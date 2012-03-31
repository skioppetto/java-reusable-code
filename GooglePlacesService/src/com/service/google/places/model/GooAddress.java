package com.service.google.places.model;

import java.util.Set;

public class GooAddress {

	private String formattedAddress;
	private Set<? extends GooAddressItem> addressItems;

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
	public Set<? extends GooAddressItem> getAddressItems() {
		return addressItems;
	}

	public  void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public  void setAddressItems(Set<? extends GooAddressItem> addressItems) {
		this.addressItems = addressItems;
	}

}
