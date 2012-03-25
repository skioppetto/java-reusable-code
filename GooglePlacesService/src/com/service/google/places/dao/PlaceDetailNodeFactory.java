package com.service.google.places.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import com.service.google.places.builder.PlaceDetailFactory;
import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooPlaceDetail;

public class PlaceDetailNodeFactory extends PlaceDetailFactory {

	private GraphDatabaseService graphDb;

	public PlaceDetailNodeFactory(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

	@Override
	public GooPlaceDetail createGooPlaceDetail(String key) {
		GraphDbTxManager.getInstance().beginOrAppendToTx(graphDb);
		Node node = GooPlaceIndexes.getInstance()
				.findGooPlaceNode(key, graphDb);
		return new GooPlaceNode((node == null) ? graphDb.createNode() : node);
	}

	@Override
	public GooAddress createGooPlaceAddress() {
		return new GooAddressWrapper();
	}

	@Override
	public GooAddressItem createGooAddressItem(String key) {
		GraphDbTxManager.getInstance().beginOrAppendToTx(graphDb);
		Node node = GooPlaceIndexes.getInstance().findAddressItemNode(key,
				graphDb);
		return new GooAddressItemNode((node == null) ? graphDb.createNode()
				: node);
	}

}
