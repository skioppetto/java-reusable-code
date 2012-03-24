package com.service.google.places;

 public class PlacesEngineRequestParameters {

	private Boolean fromDeviceUsingSensor;
	private GooLanguage language;
	private String key;

	 PlacesEngineRequestParameters() {
		super();
	}

	public Boolean getFromDeviceUsingSensor() {
		return fromDeviceUsingSensor;
	}

	public void setFromDeviceUsingSensor(Boolean fromDeviceUsingSensor) {
		this.fromDeviceUsingSensor = fromDeviceUsingSensor;
	}

	public GooLanguage getLanguage() {
		return language;
	}

	public void setLanguage(GooLanguage language) {
		this.language = language;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}