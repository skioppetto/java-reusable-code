package com.service.google.places;

public class GooPlaceDetail extends GooPlaceSuggestItem
{

	private GooResponseStatus status;
	private GooAddress address;
	private String formattedPhoneNumber;
	private String internationalPhoneNumber;
	private String urlPlace;
	private String urlGoogle;

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

	protected void setStatus(GooResponseStatus status) {
		this.status = status;
	}

	protected void setAddress(GooAddress address) {
		this.address = address;
	}

	protected void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	protected void setInternationalPhoneNumber(String internationalPhoneNumber) {
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	protected void setUrlPlace(String urlPlace) {
		this.urlPlace = urlPlace;
	}

	protected void setUrlGoogle(String urlGoogle) {
		this.urlGoogle = urlGoogle;
	}

}
