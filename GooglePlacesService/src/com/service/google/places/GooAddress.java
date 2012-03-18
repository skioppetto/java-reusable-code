package com.service.google.places;

import java.util.List;

public class GooAddress {

	private String formattedAddress;
	private List<GooAddressItem> addressItems;

	protected GooAddress() {
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public List<GooAddressItem> getAddressItems() {
		return addressItems;
	}

	 void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	 void setAddressItems(List<GooAddressItem> addressItems) {
		this.addressItems = addressItems;
	}

}
