package com.service.google.places.dao;

import java.util.List;

import com.service.google.places.GooAddress;
import com.service.google.places.GooAddressItem;

public class GooAddressWrapper extends GooAddress {

	public GooAddressWrapper(String address, List<GooAddressItem> items) {
		setAddressItems(items);
		setFormattedAddress(address);
	}

	public GooAddressWrapper() {
		
	}

}
