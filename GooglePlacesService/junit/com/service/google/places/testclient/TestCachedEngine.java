package com.service.google.places.testclient;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import scala.actors.threadpool.Arrays;

import com.service.google.places.GooPlacesCachedEngine;
import com.service.google.places.GooPlacesEngine;
import com.service.google.places.PlacesEngineException;
import com.service.google.places.dao.GooPlacesDaoImpl;
import com.service.google.places.dao.IGooPlacesDao;
import com.service.google.places.dao.PlaceDetailNodeFactory;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.model.GooPlaceSuggestItem;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.model.GooResponseStatus;
import com.service.google.places.model.MyApplicationKey;
import com.service.google.places.model.MyLocation;
import com.service.google.places.parser.XPathPlaceDetailBuilder;
import com.service.google.places.parser.XPathPlaceSuggestBuilder;
import com.service.google.places.request.GooDistance;
import com.service.google.places.request.GooDistance.Unit;
import com.service.google.places.request.GooPlaceCachedParameters;
import com.service.google.places.request.GooSuggestParameters;

public class TestCachedEngine {

	GooPlacesCachedEngine cachedEngine;
	private GraphDatabaseService db;

	@Before
	public void initBeans() {
		GooPlacesEngine engine = new GooPlacesEngine();
		GraphDatabaseService gDb = new EmbeddedGraphDatabase("test-db");
		db = gDb;
		IGooPlacesDao dao = new GooPlacesDaoImpl(db);
		engine.setPlaceDetailFactory(new PlaceDetailNodeFactory(db));
		engine.setSuggestBuilder(new XPathPlaceSuggestBuilder());
		engine.setDetailBuilder(new XPathPlaceDetailBuilder());
		cachedEngine = new GooPlacesCachedEngine(engine, dao);

	}

	@After
	public void closeDb() {
		db.shutdown();
	}

	static Set<String> lookUpUids;

	@SuppressWarnings("unchecked")
	@Test
	public void testCached() throws PlacesEngineException {
		GooSuggestParameters suggParams = new GooSuggestParameters();
		suggParams.setCoordinates(MyLocation.location);
		suggParams.setKey(MyApplicationKey.key);
		suggParams.setFromDeviceUsingSensor(false);
		suggParams.setRadius(new GooDistance(1000, Unit.Meters));

		suggParams.setExcludedTypes(Arrays
				.asList(new GooPlacesType[] { GooPlacesType.street_number }));
		GooPlaceSuggest suggests = cachedEngine.suggestPlaces(suggParams);
		Assert.assertEquals(GooResponseStatus.OK, suggests.getStatus());
		Assert.assertTrue(suggests.getItems().size() > 0);
		lookUpUids = new HashSet<String>();
		for (GooPlaceSuggestItem s : suggests.getItems()) {
			GooPlaceCachedParameters detPar = new GooPlaceCachedParameters();
			detPar.setFromDeviceUsingSensor(false);
			detPar.setKey(MyApplicationKey.key);
			detPar.setReference(s.getReference());
			detPar.setUid(s.getUid());
			lookUpUids.add(s.getUid());
			GooPlaceDetail det = cachedEngine.getOrCacheDetail(detPar);
			Assert.assertEquals(det, cachedEngine.getByUid(s.getUid()));

		}
	}

//	@Test
//	public void reconnectAndLookUpIndexes() {
//		for (String uid : lookUpUids) {
//			GooPlaceDetail val = cachedEngine.getByUid(uid);
//			for (GooAddressItem n : val.getAddress().getAddressItems()) {
//
//			}
//			Assert.assertNotNull(val);
//		}
//	}

}
