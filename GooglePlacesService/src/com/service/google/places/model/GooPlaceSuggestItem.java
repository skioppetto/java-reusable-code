package com.service.google.places.model;

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

	public void setName(String name) {
		this.name = name;
	}

	public void setSimplifiedAddress(String addressFormatted) {
		this.simplifiedAddress = addressFormatted;
	}

	public void setTypes(List<GooPlacesType> types) {
		this.types = types;
	}

	public void setCoordinates(GooCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GooPlaceSuggestItem other = (GooPlaceSuggestItem) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}