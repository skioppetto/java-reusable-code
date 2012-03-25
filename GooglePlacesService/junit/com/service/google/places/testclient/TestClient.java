package com.service.google.places.testclient;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.service.google.places.GooPlacesEngine;
import com.service.google.places.PlacesEngineException;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.model.GooPlaceSuggestItem;
import com.service.google.places.model.GooResponseStatus;
import com.service.google.places.model.MyApplicationKey;
import com.service.google.places.model.MyLocation;
import com.service.google.places.parser.XPathPlaceDetailBuilder;
import com.service.google.places.parser.XPathPlaceSuggestBuilder;
import com.service.google.places.request.GooDetailParameters;
import com.service.google.places.request.GooDistance;
import com.service.google.places.request.GooSuggestParameters;
import com.service.google.places.request.GooDistance.Unit;

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

	@Before
	public void init(){
		engine.setSuggestBuilder( new XPathPlaceSuggestBuilder());
		engine.setDetailBuilder (new XPathPlaceDetailBuilder());
	}
	
	@Test
	public void getSuggestionsAndDetailsTest() throws PlacesEngineException {
		
		
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
	
	@Test
	public void testJsonSuggestResult () throws PlacesEngineException{
		GooSuggestParameters suggParams = new GooSuggestParameters();
		suggParams.setCoordinates(MyLocation.location);
		suggParams.setKey(MyApplicationKey.key);
		suggParams.setFromDeviceUsingSensor(false);
		suggParams.setRadius(new GooDistance(1000, Unit.Meters));
		String suggests = engine.suggestPlacesJson(suggParams);
		Assert.assertNotNull(suggests);
		Assert.assertTrue(suggests.contains("\"name\" : \"Pyrmont\""));
		
	}
	
	@Test
	public void testXmlSuggestResult () throws PlacesEngineException{
		GooSuggestParameters suggParams = new GooSuggestParameters();
		suggParams.setCoordinates(MyLocation.location);
		suggParams.setKey(MyApplicationKey.key);
		suggParams.setFromDeviceUsingSensor(false);
		suggParams.setRadius(new GooDistance(1000, Unit.Meters));
		String suggests = engine.suggestPlacesXml(suggParams);
		Assert.assertNotNull(suggests);
		Assert.assertTrue(suggests.contains("<name>Pyrmont</name>"));
		
	}

}
