package com.service.google.places.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import com.service.google.places.GooPlaceDetail;
import com.service.google.places.IGooPlacesDao;

public class GooPlacesDaoImpl implements IGooPlacesDao {

	private static final String IDX_GOOGLE_UID = "googleId";
	private GraphDatabaseService graphDb;
	private Index<Node> googleUidIndex;
	private GraphDbTxManager txManager;

	public GooPlacesDaoImpl(GraphDbTxManager txManager) {
		this.txManager = txManager;
		setGraphDb(txManager.getGraphDb());
	}

	public void save(GooPlaceDetail detail) {
		googleUidIndex.add(((GooPlaceNode) detail).getUnderlyingNode(),
				GooPlaceNode.UID, detail.getUid());
		txManager.commiTx();
	}

	public GooPlaceDetail getByUid(String uid) {
		txManager.beginOrAppendToTx();
		IndexHits<Node> hits = googleUidIndex.get(GooPlaceNode.UID, uid);
		Node node = null;
		GooPlaceNode retVal = null;
		if ((node = hits.getSingle()) != null) {
			retVal = new GooPlaceNode(node);
		}
		txManager.commiTx();
		return retVal;
	}

	private void initIndexes(GraphDatabaseService graphDb) {
		googleUidIndex = graphDb.index().forNodes(IDX_GOOGLE_UID);

	}

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public void setGraphDb(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
		initIndexes(graphDb);
	}

}
