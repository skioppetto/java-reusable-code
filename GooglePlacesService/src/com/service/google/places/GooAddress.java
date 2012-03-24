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

	protected  void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	protected  void setAddressItems(List<GooAddressItem> addressItems) {
		this.addressItems = addressItems;
	}

}
