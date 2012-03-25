package com.service.google.places.request;

import java.util.List;

import com.service.google.places.model.GooCoordinates;
import com.service.google.places.model.GooPlacesType;

public class GooSuggestParameters extends GooBaseParameters {

	private GooCoordinates coordinates;
	private GooDistance radius;
	private String allFieldsSearchTerm;
	private String placeNameSearchTerm;
	private List<GooPlacesType> types;

	public GooCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GooCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public GooDistance getRadius() {
		return radius;
	}

	public void setRadius(GooDistance radius) {
		this.radius = radius;
	}

	public String getAllFieldsSearchTerm() {
		return allFieldsSearchTerm;
	}

	public void setAllFieldsSearchTerm(String allFieldsSearchTerm) {
		this.allFieldsSearchTerm = allFieldsSearchTerm;
	}

	public String getPlaceNameSearchTerm() {
		return placeNameSearchTerm;
	}

	public void setPlaceNameSearchTerm(String placeNameSearchTerm) {
		this.placeNameSearchTerm = placeNameSearchTerm;
	}

	
	public List<GooPlacesType> getTypes() {
		return types;
	}

	public void setTypes(List<GooPlacesType> types) {
		this.types = types;
	}

}
