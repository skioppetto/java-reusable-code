package com.service.google.places.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scala.actors.threadpool.Arrays;

import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooCoordinates;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.model.GooResponseStatus;

class GooPlaceAddressItemTest extends GooAddressItem {

	static final String[] longs = new String[] { "Veneto", "Vicenza" };
	static final String[] shorts = new String[] { "VEN", "VI" };
	static final GooPlacesType[][] types = new GooPlacesType[][] {
			{ GooPlacesType.administrative_area_level_1,
					GooPlacesType.political },
			{ GooPlacesType.administrative_area_level_2,
					GooPlacesType.local_government_office } };

	private int idx;

	public GooPlaceAddressItemTest(int idx) {
		super();
		this.idx = idx;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<GooPlacesType> getTypes() {
		return new HashSet(Arrays.asList(types[idx]));
	}

	@Override
	public String getValue() {
		return longs[idx];
	}

	@Override
	public String getShortValue() {
		return shorts[idx];
	}

}

class GooPlaceAddressTest extends GooAddress {

	@Override
	public String getFormattedAddress() {

		return "Via Contrà Lodi 17";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<GooAddressItem> getAddressItems() {
		return new HashSet(Arrays.asList(new Object[] { new GooPlaceAddressItemTest(0),
				new GooPlaceAddressItemTest(1) }));
	}

}

public class GooPlaceTestData extends GooPlaceDetail {

	@Override
	public GooResponseStatus getStatus() {
		return GooResponseStatus.OK;
	}

	@Override
	public String getFormattedPhoneNumber() {
		return "0444435434";
	}

	@Override
	public GooAddress getAddress() {
		return new GooPlaceAddressTest();
	}

	@Override
	public String getInternationalPhoneNumber() {
		return "4444333344";
	}

	@Override
	public String getUrlPlace() {
		return "http://www.google.com/place";
	}

	@Override
	public String getUrlGoogle() {
		return "http://www.google.com";
	}

	@Override
	public String getName() {
		return "myName";
	}

	@Override
	public String getSimplifiedAddress() {
		return "simpleAddress";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GooPlacesType> getTypes() {
		return Arrays.asList(new GooPlacesType[] { GooPlacesType.aquarium,
				GooPlacesType.art_gallery });
	}

	@Override
	public GooCoordinates getCoordinates() {
		return new GooCoordinates(33333, 44444);
	}

	@Override
	public Double getRating() {
		return 5.67;
	}

	@Override
	public String getIcon() {
		return "myIcon";
	}

	@Override
	public String getReference() {
		return "myReference";
	}

	@Override
	public String getUid() {
		return "thisIsMyUid";
	}

}
