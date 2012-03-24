package com.service.google.places.dao;

import java.util.List;

import org.neo4j.graphdb.Node;

import com.service.google.places.GooAddressItem;
import com.service.google.places.GooPlacesType;

public class GooAddressItemNode extends GooAddressItem {

	private static final String LONG_VALUE = "longValue";
	private static final String SHORT_VALUE = "shortValue";
	private Node underlyingNode;

	public GooAddressItemNode(Node underlyingNode) {
		super();
		this.underlyingNode = underlyingNode;
	}

	public GooAddressItemNode(Node n, GooAddressItem r) {
		this(n);
		setShort(r.getShort());
		setValue(r.getValue());
	}

	@Override
	public String getValue() {
		return (String) underlyingNode.getProperty(LONG_VALUE);
	}

	@Override
	public String getShort() {
		return (String) underlyingNode.getProperty(SHORT_VALUE);
	}

	@Override
	protected void setValue(String value) {
		underlyingNode.setProperty(LONG_VALUE, value);
	}

	@Override
	protected void setShort(String shortVal) {
		underlyingNode.setProperty(SHORT_VALUE, shortVal);
	}

	@Override
	public List<GooPlacesType> getTypes() {
		return super.getTypes();
	}

	@Override
	protected void setTypes(List<GooPlacesType> type) {
		super.setTypes(type);
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
		return o instanceof GooAddressItemNode
				&& underlyingNode.equals(((GooAddressItemNode) o)
						.getUnderlyingNode());
	}

}
