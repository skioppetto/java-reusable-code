package com.service.google.places.dao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.neo4j.test.ImpermanentGraphDatabase;


public class GooPlacesIndexesTest {

	private ImpermanentGraphDatabase graphDb;
	private GooPlaceIndexes index;

	@Before
	public void prepareTestDatabase() {
		graphDb = new ImpermanentGraphDatabase();
		index = GooPlaceIndexes.getInstance();
	}

	@After
	public void destroyTestDatabase() {
		graphDb.shutdown();
	}

	@Test
	public void addAndFindAddressItemIndex() {

		GraphDbTxManager.getInstance().beginOrAppendToTx(graphDb);
		Node addr = graphDb.createNode();
		addr.setProperty(GooAddressItemNode.LONG_VALUE, "myValue");
		index.addAddressItemIndex(addr);
		GraphDbTxManager.getInstance().commiTx(graphDb);

		Node found = index.findAddressItemNode("myValue", graphDb);
		Assert.assertNotNull(found);
		Assert.assertEquals(found.getProperty(GooAddressItemNode.LONG_VALUE),
				addr.getProperty(GooAddressItemNode.LONG_VALUE));
	}

	@Test
	public void addAndFindGooPlaceIndex() {

		GraphDbTxManager.getInstance().beginOrAppendToTx(graphDb);
		Node place = graphDb.createNode();
		place.setProperty(GooPlaceNode.UID, "myUid");
		index.addGooPlaceIndex(place);
		GraphDbTxManager.getInstance().commiTx(graphDb);

		Node found = index.findGooPlaceNode("myUid", graphDb);
		Assert.assertNotNull(found);
		Assert.assertEquals(found.getProperty(GooPlaceNode.UID),
				place.getProperty(GooPlaceNode.UID));

	}

}
