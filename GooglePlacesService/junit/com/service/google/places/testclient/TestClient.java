package com.service.google.places.testclient;

import junit.framework.Assert;

import org.junit.Test;

import com.service.google.places.GooDetailParameters;
import com.service.google.places.GooDistance;
import com.service.google.places.GooPlaceDetail;
import com.service.google.places.GooPlaceSuggest;
import com.service.google.places.GooPlaceSuggestItem;
import com.service.google.places.GooPlacesEngine;
import com.service.google.places.IPlaceDetailBuilder;
import com.service.google.places.IPlaceSuggestBuilder;
import com.service.google.places.PlacesEngineException;
import com.service.google.places.MyApplicationKey;
import com.service.google.places.MyLocation;
import com.service.google.places.GooResponseStatus;
import com.service.google.places.GooSuggestParameters;
import com.service.google.places.GooDistance.Unit;
import com.service.google.places.parser.XPathPlaceDetailBuilder;
import com.service.google.places.parser.XPathPlaceSuggestBuilder;

public class TestClient {

	GooPlacesEngine engine = new GooPlacesEngine();

	private void testSuggestion(GooPlaceSuggestItem s) {
		
		
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getCoordinates());
		Assert.assertTrue(s.getCoordinates().getLatitude() != 0);
		Assert.assertTrue(s.getCoordinates().getLongitude() != 0);
		Assert.assertNotNull(s.getIcon());
		Assert.assertTrue(s.getIcon().contains("http://"));
		Assert.assertTrue(s.getIcon().contains(".jpg")
				|| s.getIcon().contains(".png"));
		Assert.assertNotNull(s.getName());
		Assert.assertNotNull(s.getReference());
		Assert.assertNotNull(s.getSimplifiedAddress());
		Assert.assertNotNull(s.getUid());
		Assert.assertNotNull(s.getTypes());
		Assert.assertTrue(s.getTypes().size() > 0);
	}

	private void testDetail(GooPlaceDetail det) {
		Assert.assertNotNull(det);
		Assert.assertNotNull(det.getAddress());
		Assert.assertNotNull(det.getCoordinates());
		Assert.assertNotNull(det.getFormattedPhoneNumber());
		Assert.assertNotNull(det.getIcon());
		Assert.assertNotNull(det.getInternationalPhoneNumber());
		Assert.assertNotNull(det.getName());

		// TODO:
	}

	@Test
	public void getSuggestionsTest() throws PlacesEngineException {
		
		engine.setSuggestBuilder( new XPathPlaceSuggestBuilder());
		engine.setDetailBuilder (new XPathPlaceDetailBuilder());
		
		GooSuggestParameters suggParams = new GooSuggestParameters();
		suggParams.setCoordinates(MyLocation.location);
		suggParams.setKey(MyApplicationKey.key);
		suggParams.setFromDeviceUsingSensor(false);
		suggParams.setRadius(new GooDistance(1000, Unit.Meters));
		GooPlaceSuggest suggests = engine.suggestPlaces(suggParams);
		Assert.assertEquals(GooResponseStatus.OK, suggests.getStatus());
		Assert.assertTrue(suggests.getItems().size() > 0);
		for (GooPlaceSuggestItem s : suggests.getItems()) {
			testSuggestion(s);
			GooDetailParameters detPar = new GooDetailParameters();
			detPar.setFromDeviceUsingSensor(false);
			detPar.setKey(MyApplicationKey.key);
			detPar.setReference(s.getReference());
			GooPlaceDetail det = engine.getDetail(detPar);
			testDetail(det);
		}

	}

}
