package com.service.google.places.model;

import java.util.List;

public class GooAddressItem {

	private List<GooPlacesType> types;
	private String value;
	private String shortValue;

	public GooAddressItem() {
	}

	public List<GooPlacesType> getTypes() {
		return types;
	}

	public String getValue() {
		return value;
	}

	public String getShort() {
		return shortValue;
	}

	public void setTypes(List<GooPlacesType> type) {
		this.types = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setShort(String shortVal) {
		this.shortValue = shortVal;
	}

}
