package com.service.google.places.dao;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

public class GraphDbTxManager {

	private Map<GraphDatabaseService, Transaction> txMap = new HashMap<GraphDatabaseService, Transaction>();

	private GraphDbTxManager() {
		super();
	}
	
	private static GraphDbTxManager instance = null;
	
	public static GraphDbTxManager getInstance(){
		if (instance == null)
			instance = new GraphDbTxManager();
		return instance;
	}

	public void beginOrAppendToTx(GraphDatabaseService graphDb) {
		Transaction tx;
		if ((tx = txMap.get(graphDb)) == null) {
			tx = graphDb.beginTx();
			txMap.put(graphDb, tx);
		}
	}

	public void commiTx(GraphDatabaseService graphDb) {
		Transaction tx;
		if ((tx = txMap.get(graphDb)) != null) {
			tx.success();
			tx.finish();
			txMap.remove(graphDb);
		}
	}

	public void rollbackTx(GraphDatabaseService graphDb) {
		Transaction tx;
		if ((tx = txMap.get(graphDb)) != null) {
			tx.failure();
			tx.success();
			txMap.remove(graphDb);
		}
	}

}
