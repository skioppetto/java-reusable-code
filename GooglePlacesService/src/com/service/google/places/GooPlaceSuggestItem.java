package com.service.google.places;

import java.util.List;

public class GooPlaceSuggestItem {

	private String name;
	private String simplifiedAddress;
	private List<GooPlacesType> types;
	private GooCoordinates coordinates;
	private Double rating;
	private String icon;
	private String reference;
	private String uid;

	public String getName() {
		return name;
	}

	public String getSimplifiedAddress() {
		return simplifiedAddress;
	}

	public List<GooPlacesType> getTypes() {
		return types;
	}

	public GooCoordinates getCoordinates() {
		return coordinates;
	}

	public Double getRating() {
		return rating;
	}

	public String getIcon() {
		return icon;
	}

	public String getReference() {
		return reference;
	}

	public String getUid() {
		return uid;
	}

	void setName(String name) {
		this.name = name;
	}

	 void setSimplifiedAddress(String addressFormatted) {
		this.simplifiedAddress = addressFormatted;
	}

	 void setTypes(List<GooPlacesType> types) {
		this.types = types;
	}

	 void setCoordinates(GooCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	 void setRating(Double rating) {
		this.rating = rating;
	}

	 void setIcon(String icon) {
		this.icon = icon;
	}

	 void setReference(String reference) {
		this.reference = reference;
	}

	 void setUid(String uid) {
		this.uid = uid;
	}

}
