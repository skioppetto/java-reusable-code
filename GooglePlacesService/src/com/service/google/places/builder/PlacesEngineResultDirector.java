package com.service.google.places.builder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooCoordinates;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.model.GooPlaceSuggestItem;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.model.GooResponseStatus;

public class PlacesEngineResultDirector {

	private IPlaceSuggestBuilder placeSuggestBuilder;
	private IPlaceDetailBuilder placeDetailBuilder;
	private PlaceDetailFactory detailFactory = new PlaceDetailFactory();

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

	public GooPlaceDetail parseDetailPlace(InputStream stream)
			throws ResponseBuilderParseException {
		placeDetailBuilder.openStream(stream);
		String key = placeDetailBuilder.buildId();
		GooPlaceDetail detail = getDetailFactory().createGooPlaceDetail(key);
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

		GooAddress address = getDetailFactory().createGooPlaceAddress();
		List<GooAddressItem> items = new ArrayList<GooAddressItem>();
		for (int i = 0; i < placeDetailBuilder.buildAddressComponentsCount(); i++) {
			String itemkey = placeDetailBuilder
					.buildAddressComponentLongValue(i);
			GooAddressItem item = getDetailFactory().createGooAddressItem(
					itemkey);
			item.setTypes(new ArrayList<GooPlacesType>());
			for (int j = 0; j < placeDetailBuilder
					.buildAddressComponentTypesCount(i); j++)
				item.getTypes().add(
						GooPlacesType.valueOf(placeDetailBuilder
								.buildAddressComponentType(i, j)));
			item.setValue(key);
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

	public IPlaceSuggestItemBuilder getPlaceSuggestBuilder() {
		return placeSuggestBuilder;
	}

	public void setPlaceSuggestBuilder(IPlaceSuggestBuilder placeSuggestBuilder) {
		this.placeSuggestBuilder = placeSuggestBuilder;
	}

	public IPlaceDetailBuilder getPlaceDetailBuilder() {
		return placeDetailBuilder;
	}

	public void setPlaceDetailBuilder(IPlaceDetailBuilder placeDetailBuilder) {
		this.placeDetailBuilder = placeDetailBuilder;
	}

	public PlaceDetailFactory getDetailFactory() {
		return detailFactory;
	}

	public void setDetailFactory(PlaceDetailFactory detailFactory) {
		this.detailFactory = detailFactory;
	}

}
