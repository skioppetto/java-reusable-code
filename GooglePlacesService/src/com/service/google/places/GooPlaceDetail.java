package com.service.google.places;

public class GooPlaceDetail extends GooPlaceSuggestItem implements IGooStatus {

	private RequestStatus status;
	private GooAddress address;
	private String formattedPhoneNumber;
	private String internationalPhoneNumber;
	private String urlPlace;
	private String urlGoogle;

	
	@Override
	public RequestStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public GooAddress getAddress() {
		return address;
	}

	public void setAddress(GooAddress address) {
		this.address = address;
	}

	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	public String getInternationalPhoneNumber() {
		return internationalPhoneNumber;
	}

	public void setInternationalPhoneNumber(String internationalPhoneNumber) {
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	public String getUrlPlace() {
		return urlPlace;
	}

	public void setUrlPlace(String urlPlace) {
		this.urlPlace = urlPlace;
	}

	public String getUrlGoogle() {
		return urlGoogle;
	}

	public void setUrlGoogle(String urlGoogle) {
		this.urlGoogle = urlGoogle;
	}

}
