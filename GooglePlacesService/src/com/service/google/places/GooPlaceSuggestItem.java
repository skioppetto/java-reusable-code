package com.service.google.places;

import java.util.List;

public class GooPlaceSuggestItem {

	private String name;
	private String simplifiedAddress;
	private List<GooPlacesType> types;
	private Coordinates coordinates;
	private Double rating;
	private String icon;
	private String reference;
	private String uid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimplifiedAddress() {
		return simplifiedAddress;
	}

	public void setSimplifiedAddress(String addressFormatted) {
		this.simplifiedAddress = addressFormatted;
	}

	public List<GooPlacesType> getTypes() {
		return types;
	}

	public void setTypes(List<GooPlacesType> types) {
		this.types = types;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
