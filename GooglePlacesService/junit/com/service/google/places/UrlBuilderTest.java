package com.service.google.places;

import java.net.URL;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.service.google.places.Distance.Unit;

public class UrlBuilderTest {

	GooPlacesUrlBuilder builder = new GooPlacesUrlBuilder(OutputType.xml);

	@Test
	public void testBuildUrlMandatory() throws GooUrlBuilderException {
		SuggestParameters params = new SuggestParameters();
		params.setKey(ApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new Distance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		Assert.assertTrue(url.toString().indexOf("&key=" + ApplicationKey.key) >= 0
				|| url.toString().indexOf("?key=" + ApplicationKey.key) >= 0);
		Assert.assertTrue(url.toString().indexOf(
				"&location=" + MyLocation.location.getLatitude() + ","
						+ MyLocation.location.getLongitude()) >= 0
				|| url.toString().indexOf("?location="+ MyLocation.location.getLatitude() + ","
						+ MyLocation.location.getLongitude()) >= 0);
		Assert.assertTrue(url.toString().indexOf("&sensor=false") >= 0
				|| url.toString().indexOf("?sensor=false") >= 0);
		Assert.assertTrue(url.toString().indexOf("&radius=1000") >= 0
				|| url.toString().indexOf("?radius=1000") >= 0);
		System.out.println(url);
	}

	@Test
	public void testBuildUrlUOutputType() throws GooUrlBuilderException {
		builder = new GooPlacesUrlBuilder(OutputType.json);
		SuggestParameters params = new SuggestParameters();
		params.setKey(ApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new Distance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = GooUrlBuilderException.class)
	public void testBuildUrlMandatory1False() throws GooUrlBuilderException {
		SuggestParameters params = new SuggestParameters();
		params.setKey(ApplicationKey.key);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new Distance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = GooUrlBuilderException.class)
	public void testBuildUrlMandatory2False() throws GooUrlBuilderException {
		SuggestParameters params = new SuggestParameters();
		params.setKey(ApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setRadius(new Distance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = GooUrlBuilderException.class)
	public void testBuildUrlMandatoryFalse3() throws GooUrlBuilderException {
		SuggestParameters params = new SuggestParameters();
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new Distance(1000, Unit.Meters));
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test(expected = GooUrlBuilderException.class)
	public void testBuildUrlMandatoryFalse4() throws GooUrlBuilderException {
		SuggestParameters params = new SuggestParameters();
		params.setKey(ApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		System.out.println(url);
	}

	@Test
	public void testBuildUrlOptionals() throws GooUrlBuilderException {
		SuggestParameters params = new SuggestParameters();
		params.setKey(ApplicationKey.key);
		params.setCoordinates(MyLocation.location);
		params.setFromDeviceUsingSensor(false);
		params.setRadius(new Distance(1000, Unit.Meters));
		params.setLanguage(GooLanguage.ITALIAN);
		params.setTypes(Arrays.asList(
				GooPlacesType.administrative_area_level_1,
				GooPlacesType.airport));
		params.setAllFieldsSearchTerm("testKey");
		params.setPlaceNameSearchTerm("nameTest");
		URL url = builder.buildPlacesSuggestUrl(params);
		Assert.assertNotNull(url);
		Assert.assertTrue(url.toString().indexOf("&language=it") >= 0
				|| url.toString().indexOf("?language=it") >= 0);
		Assert.assertTrue(url.toString().indexOf(
				"&types=administrative_area_level_1|airport") >= 0
				|| url.toString().indexOf(
						"?types=administrative_area_level_1|airport") >= 0);
		Assert.assertTrue(url.toString().indexOf("&keyword=testKey") >= 0
				|| url.toString().indexOf("?keyword=testKey") >= 0);
		Assert.assertTrue(url.toString().indexOf("&name=nameTest") >= 0
				|| url.toString().indexOf("?name=nameTest") >= 0);
		System.out.println(url);
	}
}
