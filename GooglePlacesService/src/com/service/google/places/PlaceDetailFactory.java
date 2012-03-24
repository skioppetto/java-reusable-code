package com.service.google.places;

public class PlaceDetailFactory {

	public GooPlaceDetail createGooPlaceDetail() {
		return new GooPlaceDetail();
	}

	public GooAddress createGooPlaceAddress() {
		return new GooAddress();
	}

	public GooAddressItem createGooAddressItem() {
		return new GooAddressItem();
	}

}
