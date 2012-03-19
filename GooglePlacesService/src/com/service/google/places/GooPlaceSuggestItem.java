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

	protected void setName(String name) {
		this.name = name;
	}

	protected  void setSimplifiedAddress(String addressFormatted) {
		this.simplifiedAddress = addressFormatted;
	}

	protected  void setTypes(List<GooPlacesType> types) {
		this.types = types;
	}

	protected  void setCoordinates(GooCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	protected  void setRating(Double rating) {
		this.rating = rating;
	}

	protected  void setIcon(String icon) {
		this.icon = icon;
	}

	protected  void setReference(String reference) {
		this.reference = reference;
	}

	protected  void setUid(String uid) {
		this.uid = uid;
	}
	
	

}
