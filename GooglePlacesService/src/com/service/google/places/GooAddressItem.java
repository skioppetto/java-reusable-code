package com.service.google.places;

import java.util.List;

public class GooAddressItem {

	private List<GooPlacesType> types;
	private String value;
	private String shortValue;

	public List<GooPlacesType> getTypes() {
		return types;
	}

	public void setTypes(List<GooPlacesType> type) {
		this.types = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setShort(String shortVal) {
		this.shortValue = shortVal;
	}

	public String getShort() {
		return shortValue;
	}

}
