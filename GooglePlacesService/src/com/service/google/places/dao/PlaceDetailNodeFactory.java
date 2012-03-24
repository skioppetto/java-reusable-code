package com.service.google.places.dao;

import org.neo4j.graphdb.GraphDatabaseService;

import com.service.google.places.GooAddress;
import com.service.google.places.GooAddressItem;
import com.service.google.places.GooPlaceDetail;
import com.service.google.places.PlaceDetailFactory;

public class PlaceDetailNodeFactory extends PlaceDetailFactory {

	private GraphDbTxManager manager;
	
	public PlaceDetailNodeFactory(GraphDbTxManager tx) {
		this.manager = tx;
	}

	@Override
	public GooPlaceDetail createGooPlaceDetail() {
		manager.beginOrAppendToTx();
		return new GooPlaceNode(manager.getGraphDb().createNode());
	}

	@Override
	public GooAddress createGooPlaceAddress() {
		return new GooAddressWrapper();
	}

	@Override
	public GooAddressItem createGooAddressItem() {
		manager.beginOrAppendToTx();
		return new GooAddressItemNode(manager.getGraphDb().createNode());
	}

}
