package com.service.google.places.model;

public class GooPlaceDetail extends GooPlaceSuggestItem
{

	private GooResponseStatus status;
	private GooAddress address;
	private String formattedPhoneNumber;
	private String internationalPhoneNumber;
	private String urlPlace;
	private String urlGoogle;

	
	
	public GooPlaceDetail() {
		super();
	}

	public GooResponseStatus getStatus() {
		return status;
	}

	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}

	public GooAddress getAddress() {
		return address;
	}

	public String getInternationalPhoneNumber() {
		return internationalPhoneNumber;
	}

	public String getUrlPlace() {
		return urlPlace;
	}

	public String getUrlGoogle() {
		return urlGoogle;
	}

	public void setStatus(GooResponseStatus status) {
		this.status = status;
	}

	public void setAddress(GooAddress address) {
		this.address = address;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	public void setInternationalPhoneNumber(String internationalPhoneNumber) {
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	public void setUrlPlace(String urlPlace) {
		this.urlPlace = urlPlace;
	}

	public void setUrlGoogle(String urlGoogle) {
		this.urlGoogle = urlGoogle;
	}

}
