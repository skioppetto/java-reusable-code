package com.service.google.places;

import java.net.URL;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.service.google.places.GooDistance.Unit;

public class UrlBuilderTest {

	private GooOutputType testType = GooOutputType.xml;
	PlacesEngineUrlBuilder builder = new PlacesEngineUrlBuilder(testType);
	

	@Test
	public void testBuildSuggestUrlMandatory() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setKey(MyApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new GooDistance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		Assert.assertTrue (url.toString().contains("search/"+testType));
		Assert.assertTrue(url.toString().indexOf("?key=" + MyApplicationKey.key) >= 0);
		Assert.assertTrue(url.toString().indexOf(
				"&location=" + MyLocation.location.getLatitude() + ","
						+ MyLocation.location.getLongitude()) >= 0);
		Assert.assertTrue(url.toString().indexOf("&sensor=false") >= 0);
		Assert.assertTrue(url.toString().indexOf("&radius=1000") >= 0);
		System.out.println(url);
	}
	
	@Test
	public void testBuildDetailUrlMandatory() throws PlacesEngineUrlBuilderException {
		GooDetailParameters params = new GooDetailParameters();
		params.setKey(MyApplicationKey.key);
		params.setFromDeviceUsingSensor(true);
		params.setReference("myReference");
		URL url = builder.buildPlacesDetailUrl(params);
		Assert.assertNotNull(url);
		Assert.assertTrue (url.toString().contains("details/"+testType ));
		Assert.assertTrue(url.toString().indexOf("?key=" + MyApplicationKey.key) >= 0);
		Assert.assertTrue(url.toString().indexOf("&sensor=true") >= 0);
		Assert.assertTrue(url.toString().indexOf("&reference=myReference") >= 0);
		System.out.println(url);
	}

	@Test
	public void testBuildUrlUOutputType() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setKey(MyApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new GooDistance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = PlacesEngineUrlBuilderException.class)
	public void testBuildUrlMandatory1False() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setKey(MyApplicationKey.key);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new GooDistance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = PlacesEngineUrlBuilderException.class)
	public void testBuildUrlMandatory2False() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setKey(MyApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setRadius(new GooDistance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = PlacesEngineUrlBuilderException.class)
	public void testBuildUrlMandatoryFalse3() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new GooDistance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = PlacesEngineUrlBuilderException.class)
	public void testBuildUrlMandatoryFalse4() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setKey(MyApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test
	public void testBuildUrlOptionals() throws PlacesEngineUrlBuilderException {
		GooSuggestParameters params = new GooSuggestParameters();
		params.setKey(MyApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new GooDistance(1000, Unit.Meters));
		params.setLanguage(GooLanguage.ITALIAN);
		params.setTypes(Arrays.asList(
				GooPlacesType.administrative_area_level_1,
				GooPlacesType.airport));
		params.setAllFieldsSearchTerm("testKey");
		params.setPlaceNameSearchTerm("nameTest");
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		Assert.assertTrue(url.toString().indexOf("&language=it") >= 0);
		Assert.assertTrue(url.toString().indexOf(
				"&types=administrative_area_level_1|airport") >= 0);
		Assert.assertTrue(url.toString().indexOf("&keyword=testKey") >= 0);
		Assert.assertTrue(url.toString().indexOf("&name=nameTest") >= 0);
		System.out.println(url);
	}
}
