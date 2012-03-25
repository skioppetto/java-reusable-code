package com.service.google.places.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import com.service.google.places.model.GooPlaceDetail;

public class GooPlacesDaoImpl implements IGooPlacesDao {

	private GraphDatabaseService graphDb;

	public GooPlacesDaoImpl(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

	public void save(GooPlaceDetail detail) {
		GooPlaceIndexes.getInstance().addGooPlaceIndex(
				((GooPlaceNode) detail).getUnderlyingNode());
		GraphDbTxManager.getInstance().commiTx(graphDb);
	}

	public GooPlaceDetail getByUid(String uid) {
		GraphDbTxManager.getInstance().beginOrAppendToTx(graphDb);
		Node node = GooPlaceIndexes.getInstance().findGooPlaceNode(uid,
				graphDb);
		GooPlaceNode retVal = null;
		if (node != null) {
			retVal = new GooPlaceNode(node);
		}
		GraphDbTxManager.getInstance().commiTx(graphDb);
		return retVal;
	}

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public void setGraphDb(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

}
