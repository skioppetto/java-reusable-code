package com.service.google.places.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = PoliticalRelashionship.RELATIONSHIP_TYPE)
public class PoliticalRelashionship {
	public static final String RELATIONSHIP_TYPE = "LOCATION_CONTAINS";
	@GraphId
	private Long id;

	@StartNode
	private GooAddressItem parent;
	@EndNode
	private GooAddressItem child;
	
	private GooPlacesType type;

	public PoliticalRelashionship(){}
	public PoliticalRelashionship(GooAddressItem parent, GooAddressItem child,
			GooPlacesType type) {
		this.parent = parent;
		this.child = child;
		this.type = type;
	}

	public GooAddressItem getParent() {
		return parent;
	}

	public void setParent(GooAddressItem parent) {
		this.parent = parent;
	}

	public GooAddressItem getChild() {
		return child;
	}

	public void setChild(GooAddressItem child) {
		this.child = child;
	}

	public GooPlacesType getType() {
		return type;
	}

	public void setType(GooPlacesType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}