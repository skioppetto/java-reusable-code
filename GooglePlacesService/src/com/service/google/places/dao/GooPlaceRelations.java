package com.service.google.places.dao;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import scala.actors.threadpool.Arrays;

import com.service.google.places.model.GooPlacesType;

public class GooPlaceRelations {

	static final GooPlacesType[] politicalContainersOrder = new GooPlacesType[] {
			GooPlacesType.sublocality_level_5,
			GooPlacesType.sublocality_level_4,
			GooPlacesType.sublocality_level_3,
			GooPlacesType.sublocality_level_2,
			GooPlacesType.sublocality_level_1, GooPlacesType.sublocality,
			GooPlacesType.locality, GooPlacesType.administrative_area_level_3,
			GooPlacesType.administrative_area_level_2,
			GooPlacesType.administrative_area_level_1, GooPlacesType.country };

	static enum AddressItemRelations implements RelationshipType {
		PLACE_IS_LOCATED, LOCATION_CONTAINS
	}

	private static GooPlaceRelations instance;

	static GooPlaceRelations getInstace() {
		if (instance == null)
			instance = new GooPlaceRelations();
		return instance;
	}

	public List<GooAddressItemNode> nodeToAddressItemList(GooPlaceNode node) {
		List<GooAddressItemNode> items = new ArrayList<GooAddressItemNode>();
		Iterable<Relationship> addrItems = node.getUnderlyingNode()
				.getRelationships(AddressItemRelations.PLACE_IS_LOCATED,
						Direction.OUTGOING);
		for (Relationship r : addrItems) {
			GooAddressItemNode item = new GooAddressItemNode(r.getEndNode());
			item.setTypes(new ArrayList<GooPlacesType>());
			for (String s : (String[]) r
					.getProperty(AddressItemRelations.PLACE_IS_LOCATED
							.toString()))
				item.getTypes().add(GooPlacesType.valueOf(s));
			items.add(item);
		}
		return items;
	}

	public void buildRelations(GooPlaceNode node,
			List<GooAddressItemNode> items) {
		List<GooAddressItemNode> notNullTypes = new ArrayList<GooAddressItemNode>();
		for (GooAddressItemNode itm : items) {
			Relationship rel = node.getUnderlyingNode().createRelationshipTo(
					itm.getUnderlyingNode(),
					AddressItemRelations.PLACE_IS_LOCATED);
			String[] locTypes = new String[itm.getTypes().size()];
			int i = 0;
			for (GooPlacesType t : itm.getTypes()) {
				locTypes[i++] = t.toString();
				int idx = Arrays.asList(politicalContainersOrder).indexOf(t);
				if (idx >= 0) {
					notNullTypes.add(itm);
					/* add value to index */
					GooPlaceIndexes.getInstance().addAddressItemIndex(
							itm.getUnderlyingNode());

				}
			}
			rel.setProperty(AddressItemRelations.PLACE_IS_LOCATED.toString(),
					locTypes);
		}
		/* build relations between address political items */
		for (int j = 0; j < notNullTypes.size() - 1; j++) {
			notNullTypes
					.get(j)
					.getUnderlyingNode()
					.createRelationshipTo(
							notNullTypes.get(j + 1).getUnderlyingNode(),
							AddressItemRelations.LOCATION_CONTAINS);

		}

	}
}
