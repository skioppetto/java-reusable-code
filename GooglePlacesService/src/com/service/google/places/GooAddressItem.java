package com.service.google.places;

import java.util.List;

public class GooAddressItem {

	private List<GooPlacesType> types;
	private String value;
	private String shortValue;

	protected GooAddressItem() {
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

	 void setTypes(List<GooPlacesType> type) {
		this.types = type;
	}

	 void setValue(String value) {
		this.value = value;
	}

	 void setShort(String shortVal) {
		this.shortValue = shortVal;
	}

}
