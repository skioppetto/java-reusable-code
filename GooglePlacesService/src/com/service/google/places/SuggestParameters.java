package com.service.google.places;

import java.util.List;

public class SuggestParameters {

	private Coordinates coordinates;
	private Distance radius;
	private Boolean fromDeviceUsingSensor;
	private String key;
	private String allFieldsSearchTerm;
	private String placeNameSearchTerm;
	private GooLanguage language;
	private List<GooPlacesType> types;

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Distance getRadius() {
		return radius;
	}

	public void setRadius(Distance radius) {
		this.radius = radius;
	}

	public Boolean getFromDeviceUsingSensor() {
		return fromDeviceUsingSensor;
	}

	public void setFromDeviceUsingSensor(Boolean fromDeviceUsingSensor) {
		this.fromDeviceUsingSensor = fromDeviceUsingSensor;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public GooLanguage getLanguage() {
		return language;
	}

	public void setLanguage(GooLanguage language) {
		this.language = language;
	}

}
