package com.service.google.places.testclient;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.ImpermanentGraphDatabase;

import com.service.google.places.GooDistance;
import com.service.google.places.GooDistance.Unit;
import com.service.google.places.GooPlaceCachedParameters;
import com.service.google.places.GooPlaceDetail;
import com.service.google.places.GooPlaceSuggest;
import com.service.google.places.GooPlaceSuggestItem;
import com.service.google.places.GooPlacesCachedEngine;
import com.service.google.places.GooPlacesEngine;
import com.service.google.places.GooResponseStatus;
import com.service.google.places.GooSuggestParameters;
import com.service.google.places.IGooPlacesDao;
import com.service.google.places.MyApplicationKey;
import com.service.google.places.MyLocation;
import com.service.google.places.PlacesEngineException;
import com.service.google.places.dao.GooPlacesDaoImpl;
import com.service.google.places.dao.GraphDbTxManager;
import com.service.google.places.dao.PlaceDetailNodeFactory;
import com.service.google.places.parser.XPathPlaceDetailBuilder;
import com.service.google.places.parser.XPathPlaceSuggestBuilder;

public class TestCachedEngine {

	static GooPlacesCachedEngine cachedEngine;

	@BeforeClass
	public static void initBeans() {
		GooPlacesEngine engine = new GooPlacesEngine();
		GraphDatabaseService gDb = new ImpermanentGraphDatabase();
		GraphDbTxManager tx = new GraphDbTxManager(gDb);
		IGooPlacesDao dao = new GooPlacesDaoImpl(tx);
		engine.setPlaceDetailFactory(new PlaceDetailNodeFactory(tx));
		engine.setSuggestBuilder(new XPathPlaceSuggestBuilder());
		engine.setDetailBuilder(new XPathPlaceDetailBuilder());
		cachedEngine = new GooPlacesCachedEngine(engine, dao);

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
			GooPlaceDetail det = cachedEngine.getDetail(detPar);
			Assert.assertEquals(det, cachedEngine.getByUid(s.getUid()));
		}
	}

}
