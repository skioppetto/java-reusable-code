package com.service.google.places.model;

import java.util.List;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity()
public class GooPlaceSuggestItem {

	
	@GraphId private Long nodeId;
	private String name;
	private String simplifiedAddress;
	private List<GooPlacesType> types;
	private GooCoordinates coordinates;
	private Double rating;
	private String icon;
	private String reference;
	@Indexed
	private String uid;

	public String getName() {
		return name;
	}

	
	public String getSimplifiedAddress() {
		return simplifiedAddress;
	}

	public List<GooPlacesType> getTypes() {
		return types;
	}

	public GooCoordinates getCoordinates() {
		return coordinates;
	}

	public Double getRating() {
		return rating;
	}

	public String getIcon() {
		return icon;
	}

	public String getReference() {
		return reference;
	}

	public String getUid() {
		return uid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSimplifiedAddress(String addressFormatted) {
		this.simplifiedAddress = addressFormatted;
	}

	public void setTypes(List<GooPlacesType> types) {
		this.types = types;
	}

	public void setCoordinates(GooCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GooPlaceSuggestItem other = (GooPlaceSuggestItem) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}

	
}
