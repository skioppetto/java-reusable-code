package com.service.google.places.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.ImpermanentGraphDatabase;

import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlacesType;

public class GooPlaceDetailNeo4jBuildTest {

	private ImpermanentGraphDatabase graphDb;
	@Before
	public void prepareTestDatabase() {
		graphDb = new ImpermanentGraphDatabase();
	}

	@After
	public void destroyTestDatabase() {
		graphDb.shutdown();
	}

	@Test
	public void testBuildNode() {
		Transaction tx = graphDb.beginTx();
		Node n = graphDb.createNode();
		GooPlaceNode gNode = new GooPlaceNode(n);
		GooPlaceDetail t = new GooPlaceTestData();
		gNode.setFormattedPhoneNumber(t.getFormattedPhoneNumber());
		gNode.setIcon("myIcon");
		gNode.setInternationalPhoneNumber(t.getInternationalPhoneNumber());
		gNode.setName("myName");
		gNode.setReference("myReference");
		gNode.setUid("myId");
		gNode.setAddress(t.getAddress());
		tx.success();
		tx.finish();
		long id = gNode.getUnderlyingNode().getId();

		Assert.assertTrue(id > 0);

		GooPlaceNode loaded = new GooPlaceNode(graphDb.getNodeById(id));

		Assert.assertEquals(t.getFormattedPhoneNumber(),
				loaded.getFormattedPhoneNumber());
		Assert.assertEquals("myName", loaded.getName());
		Assert.assertEquals("myIcon", loaded.getIcon());
		Assert.assertEquals("myId", loaded.getUid());
		Assert.assertEquals("myReference", loaded.getReference());
		Assert.assertEquals(t.getAddress().getFormattedAddress(), loaded
				.getAddress().getFormattedAddress());
		Assert.assertTrue(loaded.getAddress().getAddressItems() != null);
		Assert.assertTrue(loaded.getAddress().getAddressItems().size() > 0);
		for (GooAddressItem adr : loaded.getAddress().getAddressItems()) {
			Assert.assertNotNull(adr);
			System.out.println(adr.getShort() + " " + adr.getValue());
			for (GooPlacesType gt : adr.getTypes())
				System.out.println(gt.toString());
		}

	}

}
