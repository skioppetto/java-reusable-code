package com.service.google.places.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;


public class GooPlaceIndexes {

	static final String PLACES_GOOGLE_UID = "googleId";
	static final String ADDRESS_ITEMS_LONGVALUE = "location";

	private static GooPlaceIndexes instance;

	private GooPlaceIndexes() {

	}

	static GooPlaceIndexes getInstance() {
		if (instance == null)
			instance = new GooPlaceIndexes();
		return instance;
	}

	Node findAddressItemNode(String longValue, GraphDatabaseService graphDb) {
		return graphDb.index()
				.forNodes(GooPlaceIndexes.ADDRESS_ITEMS_LONGVALUE)
				.get(GooAddressItemNode.LONG_VALUE, longValue).getSingle();
	}

	void addAddressItemIndex(Node itemAddressNode) {
		GraphDbTxManager.getInstance().beginOrAppendToTx(
				itemAddressNode.getGraphDatabase());
		itemAddressNode
				.getGraphDatabase()
				.index()
				.forNodes(GooPlaceIndexes.ADDRESS_ITEMS_LONGVALUE)
				.putIfAbsent(
						itemAddressNode,
						GooAddressItemNode.LONG_VALUE,
						itemAddressNode
								.getProperty(GooAddressItemNode.LONG_VALUE));
	}

	Node findGooPlaceNode(String uid, GraphDatabaseService graphDb) {
		return graphDb.index().forNodes(GooPlaceIndexes.PLACES_GOOGLE_UID)
				.get(GooPlaceNode.UID, uid).getSingle();
	}

	void addGooPlaceIndex(Node place) {
		GraphDbTxManager.getInstance().beginOrAppendToTx(
				place.getGraphDatabase());
		place.getGraphDatabase()
				.index()
				.forNodes(GooPlaceIndexes.PLACES_GOOGLE_UID)
				.putIfAbsent(place, GooPlaceNode.UID,
						place.getProperty(GooPlaceNode.UID));
	}
}
