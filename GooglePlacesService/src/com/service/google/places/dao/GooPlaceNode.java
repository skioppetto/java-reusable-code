package com.service.google.places.dao;

import java.util.List;

import org.neo4j.graphdb.Node;

import com.service.google.places.GooAddress;
import com.service.google.places.GooCoordinates;
import com.service.google.places.GooPlaceDetail;
import com.service.google.places.GooPlacesType;
import com.service.google.places.GooResponseStatus;

public class GooPlaceNode extends GooPlaceDetail {

	private static final String STATUS = "status";
	private static final String FORMATTED_PHONE_NUMBER = "formattedPhoneNumber";
	private static final String INTERNATIONAL_PHONE_NUMBER = "internationalPhoneNumber";
	private static final String URL_GOOGLE = "urlGoogle";
	private static final String URL_PLACE = "urlPlace";
	private static final String NAME = "name";
	private static final String VICINITY = "vicinity";
	private static final String COORDINATES_LATITUDE = "latitude";
	private static final String COORDINATES_LONGITUDE = "longitude";
	private static final String RATING = "googleRating";
	private static final String ICON = "icon";
	private static final String REFERENCE = "reference";
	private static final String UID = "uid";
	private Node underlyingNode;

	public GooPlaceNode(Node node) {
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
		// TODO: create relation
		return null;
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
		// TODO make a relation
		return super.getTypes();
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

	
}
