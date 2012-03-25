package com.service.google.places.testclient;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.service.google.places.GooPlacesCachedEngine;
import com.service.google.places.GooPlacesEngine;
import com.service.google.places.PlacesEngineException;
import com.service.google.places.dao.GooPlacesDaoImpl;
import com.service.google.places.dao.IGooPlacesDao;
import com.service.google.places.dao.PlaceDetailNodeFactory;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.model.GooPlaceSuggestItem;
import com.service.google.places.model.GooResponseStatus;
import com.service.google.places.model.MyApplicationKey;
import com.service.google.places.model.MyLocation;
import com.service.google.places.parser.XPathPlaceDetailBuilder;
import com.service.google.places.parser.XPathPlaceSuggestBuilder;
import com.service.google.places.request.GooDistance;
import com.service.google.places.request.GooPlaceCachedParameters;
import com.service.google.places.request.GooSuggestParameters;
import com.service.google.places.request.GooDistance.Unit;

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

	@Test
	public void testCached() throws PlacesEngineException {
		GooSuggestParameters suggParams = new GooSuggestParameters();
		suggParams.setCoordinates(MyLocation.location);
		suggParams.setKey(MyApplicationKey.key);
		suggParams.setFromDeviceUsingSensor(false);
		suggParams.setRadius(new GooDistance(1000, Unit.Meters));
		GooPlaceSuggest suggests = cachedEngine.suggestPlaces(suggParams);
		Assert.assertEquals(GooResponseStatus.OK, suggests.getStatus());
		Assert.assertTrue(suggests.getItems().size() > 0);
		for (GooPlaceSuggestItem s : suggests.getItems()) {
			GooPlaceCachedParameters detPar = new GooPlaceCachedParameters();
			detPar.setFromDeviceUsingSensor(false);
			detPar.setKey(MyApplicationKey.key);
			detPar.setReference(s.getReference());
			detPar.setUid(s.getUid());
			GooPlaceDetail det = cachedEngine.getCachedDetail(detPar);
			Assert.assertEquals(det, cachedEngine.getByUid(s.getUid()));
		}
	}

}
