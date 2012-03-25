package com.service.google.places.builder;

import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooPlaceDetail;

public class PlaceDetailFactory {

	public GooPlaceDetail createGooPlaceDetail(String key) {
		return new GooPlaceDetail();
	}

	public GooAddress createGooPlaceAddress() {
		return new GooAddress();
	}

	public GooAddressItem createGooAddressItem(String key) {
		return new GooAddressItem();
	}

}
