package com.service.google.places.testclient;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scala.actors.threadpool.Arrays;

import com.service.google.places.IGooPlaceCachedEngine;
import com.service.google.places.PlacesEngineException;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.model.GooPlaceSuggestItem;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.model.GooResponseStatus;
import com.service.google.places.model.MyApplicationKey;
import com.service.google.places.model.MyLocation;
import com.service.google.places.request.GooDistance;
import com.service.google.places.request.GooDistance.Unit;
import com.service.google.places.request.GooPlaceCachedParameters;
import com.service.google.places.request.GooSuggestParameters;

@RunWith(SpringJUnit4ClassRunner.class)
// @ApplicationContext will be loaded from
// "classpath:/com/example/MyTest-context.xml"
@ContextConfiguration
public class TestCachedEngine {

	@Autowired
	IGooPlaceCachedEngine cachedEngine;

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
}
