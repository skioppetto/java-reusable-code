package com.service.google.places.dao;

import java.util.List;

import com.service.google.places.model.GooAddress;

public class GooAddressWrapper extends GooAddress {

	public GooAddressWrapper(String address, List<GooAddressItemNode> items) {
		setAddressItems(items);
		setFormattedAddress(address);
	}

	public GooAddressWrapper() {
		
	}

		
}
