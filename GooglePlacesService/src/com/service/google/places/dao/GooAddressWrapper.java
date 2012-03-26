package com.service.google.places.dao;

import java.util.List;

import com.service.google.places.model.GooAddress;

public class GooAddressWrapper extends GooAddress {

	private String streetNumber;
	
	public GooAddressWrapper(String address, List<GooAddressItemNode> items) {
		setAddressItems(items);
		setFormattedAddress(address);
	}

	public GooAddressWrapper() {
		
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	
	
		
}
