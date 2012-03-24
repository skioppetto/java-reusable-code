package com.service.google.places.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

public class GraphDbTxManager {

	private GraphDatabaseService graphDb;
	private Transaction tx;

	public GraphDbTxManager(GraphDatabaseService graphDb) {
		super();
		this.setGraphDb(graphDb);

	}

	public void beginOrAppendToTx() {
		if (tx == null)
			tx = getGraphDb().beginTx();
	}

	public void commiTx() {
		if (tx != null) {
			tx.success();
			tx.finish();
			tx = null;
		}
	}

	public void rollbackTx() {
		if (tx != null) {
			tx.failure();
			tx.success();
			tx = null;
		}
	}

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public void setGraphDb(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

}
