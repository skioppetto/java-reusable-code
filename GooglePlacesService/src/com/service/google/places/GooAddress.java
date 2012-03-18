package com.service.google.places;

import java.util.List;

public class GooAddress {

	private String formattedAddress;
	private List<GooAddressItem> addressItems;

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public List<GooAddressItem> getAddressItems() {
		return addressItems;
	}

	public void setAddressItems(List<GooAddressItem> addressItems) {
		this.addressItems = addressItems;
	}

}
