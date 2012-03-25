package com.service.google.places.model;

import java.util.List;

public class GooAddress {

	private String formattedAddress;
	private List<? extends GooAddressItem> addressItems;

	public  GooAddress() {
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	/***
	 * List of address Items: street number, route, locality and
	 * many others
	 * @return
	 */
	public List<? extends GooAddressItem> getAddressItems() {
		return addressItems;
	}

	public  void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public  void setAddressItems(List<? extends GooAddressItem> addressItems) {
		this.addressItems = addressItems;
	}

}
