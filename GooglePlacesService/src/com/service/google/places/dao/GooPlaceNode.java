package com.service.google.places.dao;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;

import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooCoordinates;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.model.GooResponseStatus;

public class GooPlaceNode extends GooPlaceDetail {

	static final String STATUS = "status";
	static final String FORMATTED_PHONE_NUMBER = "formattedPhoneNumber";
	static final String INTERNATIONAL_PHONE_NUMBER = "internationalPhoneNumber";
	static final String URL_GOOGLE = "urlGoogle";
	static final String URL_PLACE = "urlPlace";
	static final String NAME = "name";
	static final String VICINITY = "vicinity";
	static final String COORDINATES_LATITUDE = "latitude";
	static final String COORDINATES_LONGITUDE = "longitude";
	static final String RATING = "googleRating";
	static final String ICON = "icon";
	static final String REFERENCE = "reference";
	static final String UID = "uid";
	static final String TYPES = "types";
	static final String FORMATTED_ADDRESS = "formattedAddress";

	private Node underlyingNode;

	GooPlaceNode(Node node) {
		this.underlyingNode = node;
	}

	@Override
	public GooResponseStatus getStatus() {
		return GooResponseStatus.valueOf((String) underlyingNode
				.getProperty(STATUS));
	}

	@Override
	public String getFormattedPhoneNumber() {

		return (String) underlyingNode.getProperty(FORMATTED_PHONE_NUMBER);
	}

	@Override
	public GooAddress getAddress() {
		String address = (String) underlyingNode.getProperty(FORMATTED_ADDRESS);
		return new GooAddressWrapper(address, GooPlaceRelations.getInstace()
				.nodeToAddressItemList(this));
	}

	@Override
	public String getInternationalPhoneNumber() {
		return (String) underlyingNode.getProperty(INTERNATIONAL_PHONE_NUMBER);
	}

	@Override
	public String getUrlPlace() {
		return (String) underlyingNode.getProperty(URL_PLACE);
	}

	@Override
	public String getUrlGoogle() {
		return (String) underlyingNode.getProperty(URL_GOOGLE);
	}

	@Override
	public String getName() {
		return (String) underlyingNode.getProperty(NAME);
	}

	@Override
	public String getSimplifiedAddress() {
		return (String) underlyingNode.getProperty(VICINITY);
	}

	@Override
	public List<GooPlacesType> getTypes() {
		String[] types = (String[]) underlyingNode.getProperty(TYPES);
		List<GooPlacesType> array = new ArrayList<GooPlacesType>();
		for (String t : types)
			array.add(GooPlacesType.valueOf(t));
		return array;
	}

	@Override
	public GooCoordinates getCoordinates() {
		double lat = (Double) underlyingNode.getProperty(COORDINATES_LATITUDE);
		double lng = (Double) underlyingNode.getProperty(COORDINATES_LONGITUDE);
		return new GooCoordinates(lat, lng);
	}

	@Override
	public Double getRating() {
		return (Double) underlyingNode.getProperty(RATING);
	}

	@Override
	public String getIcon() {
		return (String) underlyingNode.getProperty(ICON);
	}

	@Override
	public String getReference() {
		return (String) underlyingNode.getProperty(REFERENCE);
	}

	@Override
	public String getUid() {
		return (String) underlyingNode.getProperty(UID);
	}

	@Override
	public void setStatus(GooResponseStatus status) {
		underlyingNode.setProperty(STATUS, status.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAddress(GooAddress address) {
		underlyingNode.setProperty(FORMATTED_ADDRESS,
				address.getFormattedAddress());
		GooPlaceRelations.getInstace().buildRelations(this,
				(List<GooAddressItemNode>) address.getAddressItems());
	}

	@Override
	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		underlyingNode
				.setProperty(FORMATTED_PHONE_NUMBER, formattedPhoneNumber);
	}

	@Override
	public void setInternationalPhoneNumber(String internationalPhoneNumber) {
		underlyingNode.setProperty(INTERNATIONAL_PHONE_NUMBER,
				internationalPhoneNumber);
	}

	@Override
	public void setUrlPlace(String urlPlace) {
		underlyingNode.setProperty(URL_PLACE, urlPlace);
	}

	@Override
	public void setUrlGoogle(String urlGoogle) {
		underlyingNode.setProperty(URL_GOOGLE, urlGoogle);
	}

	@Override
	public void setName(String name) {
		underlyingNode.setProperty(NAME, name);
	}

	@Override
	public void setSimplifiedAddress(String addressFormatted) {
		underlyingNode.setProperty(VICINITY, addressFormatted);
	}

	@Override
	public void setTypes(List<GooPlacesType> types) {
		String[] array = new String[types.size()];
		int i = 0;
		for (GooPlacesType p : types)
			array[i++] = p.toString();
		underlyingNode.setProperty(TYPES, array);
	}

	@Override
	public void setCoordinates(GooCoordinates coordinates) {
		underlyingNode.setProperty(COORDINATES_LATITUDE,
				coordinates.getLatitude());
		underlyingNode.setProperty(COORDINATES_LONGITUDE,
				coordinates.getLongitude());
	}

	@Override
	public void setRating(Double rating) {
		underlyingNode.setProperty(RATING, (rating != null) ? rating : 0);
	}

	@Override
	public void setIcon(String icon) {
		underlyingNode.setProperty(ICON, icon);
	}

	@Override
	public void setReference(String reference) {
		underlyingNode.setProperty(REFERENCE, reference);
	}

	@Override
	public void setUid(String uid) {
		underlyingNode.setProperty(UID, uid);
	}

	Node getUnderlyingNode() {
		return underlyingNode;
	}

	void setUnderlyingNode(Node underlyingNode) {
		this.underlyingNode = underlyingNode;
	}

	@Override
	public int hashCode() {
		return underlyingNode.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof GooPlaceNode
				&& underlyingNode
						.equals(((GooPlaceNode) o).getUnderlyingNode());
	}

}
