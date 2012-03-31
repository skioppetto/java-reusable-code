package com.service.google.places.model;

import java.util.Set;

import org.springframework.data.neo4j.annotation.RelatedTo;

public class GooPlaceDetail extends GooPlaceSuggestItem {

	private GooResponseStatus status;
	private String formattedPhoneNumber;
	private String internationalPhoneNumber;
	private String urlPlace;
	private String urlGoogle;
	private String formattedAddress;
	@RelatedTo(type = "LOCATED") private Set<GooAddressItem> items;

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
		GooAddress ad = new GooAddress();
		ad.setFormattedAddress(getFormattedAddress());
		ad.setAddressItems(getItems());
		return ad;
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

	public String getFormattedAddress() {
		return formattedAddress;
	}

	
	public Set<GooAddressItem> getItems() {
		return items;
	}

	public void setStatus(GooResponseStatus status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	public void setAddress(GooAddress address) {
		setFormattedAddress(address.getFormattedAddress());
		setItems((Set<GooAddressItem>) address.getAddressItems());
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

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public void setItems(Set<GooAddressItem> items) {
		this.items = items;
	}

}
