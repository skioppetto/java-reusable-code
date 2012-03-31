package com.service.google.places.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class GooAddressItem {

	@GraphId
	private Long nodeId;
	private Set<GooPlacesType> types;
	private GooPlacesType treeType;
	@Indexed
	private String value;
	private String shortValue;
	private Set<PoliticalRelashionship> children;

	@RelatedToVia(type = "LOCATION_CONTAINS", direction = Direction.OUTGOING)
	public Set<PoliticalRelashionship> getChildren() {
		return children;
	}

	
	public PoliticalRelashionship parentOf(GooAddressItem item, GooPlacesType type) {
		final PoliticalRelashionship rel = new PoliticalRelashionship(this, item, type);
		if (children == null)
			children = new HashSet<PoliticalRelashionship>();
		children.add(rel);
		return rel;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public Set<GooPlacesType> getTypes() {
		return types;
	}

	public String getValue() {
		return value;
	}

	public String getShortValue() {
		return shortValue;
	}

	public GooPlacesType getTreeType() {
		return treeType;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setShortValue(String shortValue) {
		this.shortValue = shortValue;
	}

	public void setChildren(Set<PoliticalRelashionship> children) {
		this.children = children;
	}

	public void setTypes(Set<GooPlacesType> type) {
		this.types = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setShort(String shortVal) {
		this.shortValue = shortVal;
	}

	public void setTreeType(GooPlacesType treeType) {
		this.treeType = treeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GooAddressItem other = (GooAddressItem) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}

}
