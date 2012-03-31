package com.service.google.places.builder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooCoordinates;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.model.GooPlaceSuggestItem;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.model.GooResponseStatus;

@Component
public class PlacesEngineResultDirector {

	@Autowired
	private IPlaceSuggestBuilder placeSuggestBuilder;
	@Autowired
	private IPlaceDetailBuilder placeDetailBuilder;
	@Autowired
	private PlaceDetailFactory detailFactory;

	public GooPlaceSuggest parseSuggest(InputStream stream)
			throws ResponseBuilderParseException {

		placeSuggestBuilder.openStream(stream);
		GooPlaceSuggest suggest = new GooPlaceSuggest();
		GooResponseStatus status = GooResponseStatus
				.valueOf(placeSuggestBuilder.buildStatus());
		suggest.setStatus(status);

		if (!suggest.getStatus().equals(GooResponseStatus.OK))
			return suggest;
		List<GooPlaceSuggestItem> results = new ArrayList<GooPlaceSuggestItem>();
		for (int i = 0; i < placeSuggestBuilder.buildResultsCount(); i++) {
			GooPlaceSuggestItem item = new GooPlaceSuggestItem();
			placeSuggestBuilder.setItemId(i);
			parseSuggestItem(placeSuggestBuilder, item);
			results.add(item);
		}
		suggest.setItems(results);
		return suggest;
	}

	@Transactional
	public GooPlaceDetail parseDetailPlace(InputStream stream)
			throws ResponseBuilderParseException {
		placeDetailBuilder.openStream(stream);
		String key = placeDetailBuilder.buildId();
		GooPlaceDetail detail = detailFactory.createGooPlaceDetail(key);
		GooResponseStatus status = GooResponseStatus.valueOf(placeDetailBuilder
				.buildStatus());
		detail.setStatus(status);
		if (!detail.getStatus().equals(GooResponseStatus.OK))
			return detail;
		parseSuggestItem(placeDetailBuilder, detail);
		detail.setFormattedPhoneNumber(placeDetailBuilder
				.buildFormattedPhoneNumber());
		detail.setInternationalPhoneNumber(placeDetailBuilder
				.buildInternationalPhoneNumber());
		detail.setUrlGoogle(placeDetailBuilder.buildUrlGoolge());
		detail.setUrlPlace(placeDetailBuilder.buildUrlPlace());

		GooAddress address = detailFactory.createGooPlaceAddress();
		Set<GooAddressItem> items = new HashSet<GooAddressItem>();
		for (int i = 0; i < placeDetailBuilder.buildAddressComponentsCount(); i++) {
			String itemkey = placeDetailBuilder
					.buildAddressComponentLongValue(i);
			GooAddressItem item = detailFactory.createGooAddressItem(itemkey);
			item.setTypes(new HashSet<GooPlacesType>());
			for (int j = 0; j < placeDetailBuilder
					.buildAddressComponentTypesCount(i); j++)
				item.getTypes().add(
						GooPlacesType.valueOf(placeDetailBuilder
								.buildAddressComponentType(i, j)));
			item.setValue(itemkey);
			item.setShort(placeDetailBuilder.buildAddressComponentShortValue(i));
			items.add(item);
		}

		address.setAddressItems(items);
		address.setFormattedAddress(placeDetailBuilder.buildFormattedAddress());
		detail.setAddress(address);

		return detail;
	}

	private static void parseSuggestItem(IPlaceSuggestItemBuilder builder,
			GooPlaceSuggestItem item) {
		item.setSimplifiedAddress(builder.buildVicinity());
		item.setCoordinates(new GooCoordinates(builder.buildGeometryLatitude(),
				builder.buildGeometryLongitude()));
		item.setIcon(builder.buildIcon());
		item.setName(builder.buildName());
		item.setRating(builder.buildRating());
		item.setReference(builder.buildReference());
		List<GooPlacesType> types = new ArrayList<GooPlacesType>();
		for (int j = 0; j < builder.buildTypesCount(); j++) {
			GooPlacesType type = GooPlacesType.valueOf(builder.buildType(j));
			types.add(type);
		}
		item.setTypes(types);
		item.setUid(builder.buildId());
	}

}
