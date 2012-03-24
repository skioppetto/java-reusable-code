package com.service.google.places.dao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.ImpermanentGraphDatabase;

import com.service.google.places.GooPlaceDetail;
import com.service.google.places.IGooPlacesDao;

public class GooPlaceDaoTest {

	IGooPlacesDao dao;

	private ImpermanentGraphDatabase graphDb;

	@Before
	public void prepareTestDatabase() {
		graphDb = new ImpermanentGraphDatabase();
		GraphDbTxManager txManager = new GraphDbTxManager(graphDb);
		dao = new GooPlacesDaoImpl(txManager);
	}

	@After
	public void destroyTestDatabase() {
		graphDb.shutdown();
	}

	@Test
	public void saveAndLoadTest() {
		Transaction tx = graphDb.beginTx();
		Node n = graphDb.createNode();
		GooPlaceNode data = new GooPlaceNode(n);
		GooPlaceDetail t = new GooPlaceTestData();
		data.setFormattedPhoneNumber(t.getFormattedPhoneNumber());
		data.setIcon(t.getIcon());
		data.setInternationalPhoneNumber(t.getInternationalPhoneNumber());
		data.setName(t.getName());
		data.setReference(t.getReference());
		data.setUid(t.getUid());
		data.setSimplifiedAddress(t.getSimplifiedAddress());
		data.setUrlGoogle(t.getUrlGoogle());
		data.setUrlPlace(t.getUrlPlace());
		data.setAddress(t.getAddress());
		data.setCoordinates(t.getCoordinates());
		data.setRating(t.getRating());
		data.setStatus(t.getStatus());
		data.setTypes(t.getTypes());
		tx.success();
		tx.finish();
		long id = data.getUnderlyingNode().getId();
		Assert.assertTrue(id > 0);
		dao.save(data);

		GooPlaceDetail loaded = dao.getByUid(data.getUid());
		// GooPlaceDetail loaded = new GooPlaceNode(graphDb.getNodeById(id));
		Assert.assertNotNull(loaded);
		Assert.assertEquals(data.getName(), loaded.getName());
		Assert.assertEquals(data.getFormattedPhoneNumber(),
				loaded.getFormattedPhoneNumber());
		Assert.assertEquals(data.getIcon(), loaded.getIcon());
		Assert.assertEquals(data.getInternationalPhoneNumber(),
				loaded.getInternationalPhoneNumber());
		Assert.assertEquals(data.getReference(), loaded.getReference());
		Assert.assertEquals(data.getSimplifiedAddress(),
				loaded.getSimplifiedAddress());
		Assert.assertEquals(data.getUid(), loaded.getUid());
		Assert.assertEquals(data.getUrlGoogle(), loaded.getUrlGoogle());
		Assert.assertEquals(data.getUrlPlace(), loaded.getUrlPlace());
		Assert.assertEquals(data.getAddress().getFormattedAddress(), loaded
				.getAddress().getFormattedAddress());
		Assert.assertEquals(data.getAddress().getAddressItems().size(), loaded
				.getAddress().getAddressItems().size());
		Assert.assertEquals(data.getCoordinates().getLatitude(), loaded
				.getCoordinates().getLatitude());
		Assert.assertEquals(data.getCoordinates().getLongitude(), loaded
				.getCoordinates().getLongitude());
		Assert.assertEquals(data.getRating(), loaded.getRating());
		Assert.assertEquals(data.getStatus(), loaded.getStatus());
		Assert.assertEquals(data.getTypes().size(), loaded.getTypes().size());

	}

	@Test
	public void testNotExistsUid() {
		GooPlaceDetail loaded = dao.getByUid("XXXXXX");
		Assert.assertNull(loaded);
	}

}
