package com.service.google.places.parser;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.google.places.builder.IPlaceDetailBuilder;
import com.service.google.places.builder.ResponseBuilderParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/GooglePlaceService-context.xml" })
public class XPathDetailsTest {

	@Autowired
	private IPlaceDetailBuilder builder;
	private InputStream stream;

	@Before
	public void loadTestStream() {
		stream = XPathDetailsTest.class
				.getResourceAsStream("GooglePlaceDetailResponse.xml");
	}

	@After
	public void closeTestStream() throws IOException {
		stream.close();
	}

	@Test
	public void loadStream() throws ResponseBuilderParseException {
		builder.openStream(stream);
	}

	@Test
	public void buildStatus() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String status = builder.buildStatus();
		Assert.assertEquals("OK", status);
	}

	@Test
	public void buildVicinity() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String vicinity = builder.buildVicinity();
		Assert.assertEquals("Strada delle Cattane, 71, Vicenza", vicinity);
	}

	@Test
	public void buildGeometryLatitude() throws ResponseBuilderParseException {
		builder.openStream(stream);
		double value = builder.buildGeometryLatitude();
		Assert.assertEquals(45.5473010, value);

	}

	@Test
	public void buildGeometryLongitude() throws ResponseBuilderParseException {
		builder.openStream(stream);
		double value = builder.buildGeometryLongitude();
		Assert.assertEquals(11.5106300, value);
	}

	@Test
	public void buildIcon() throws ResponseBuilderParseException {
		builder.openStream(stream);

		String icon = builder.buildIcon();
		Assert.assertEquals(
				"http://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png",
				icon);

	}

	@Test
	public void buildName() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String name = builder.buildName();
		Assert.assertEquals("Auchan di Vicenza", name);

	}

	@Test
	public void buildRating() throws ResponseBuilderParseException {
		builder.openStream(stream);
		Double value = builder.buildRating();
		Assert.assertEquals(null, value);

	}

	@Test
	public void buildReference() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String refer = builder.buildReference();
		Assert.assertEquals(
				"CnRpAAAAbtX5XDS2ppXQ9ba4VOU-cy5ZupKI2r600XGhItEmFyiF0QxLPhFNiEiVStJG_j3DM2flTHxuNwzGoMu5V7xX-j5WvwJauXkD8nNM4jjMT12XPJD3l8OdXFZxKEHThNFyghtAb5Kf_r3j5HoLvRKB-hIQBrk9Qvpa9-GEGI8nTeAQrhoUcKYJuqtoG8ngysKYvWaP_LtWfrk",
				refer);

	}

	@Test
	public void buildType() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String type = builder.buildType(2);
		Assert.assertEquals("finance", type);

	}

	@Test
	public void buildTypesCount() throws ResponseBuilderParseException {
		builder.openStream(stream);
		int countT = builder.buildTypesCount();
		Assert.assertEquals(4, countT);
	}

	@Test
	public void buildId() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildId();
		Assert.assertEquals("4a40cfdbc9b56427a363c4a2c5aa05eb2c45d7d4", id);

	}

	@Test
	public void buildFormattedPhoneNumber()
			throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildFormattedPhoneNumber();
		Assert.assertEquals("0444 286311", id);
	}

	@Test
	public void buildInternationalPhoneNumber()
			throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildInternationalPhoneNumber();
		Assert.assertEquals("+39 0444 286311", id);
	}

	@Test
	public void buildUrlGoolge() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildUrlGoolge();
		Assert.assertEquals(
				"http://maps.google.com/maps/place?cid=15321521973906098671",
				id);
	}

	@Test
	public void buildUrlPlace() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildUrlPlace();
		Assert.assertEquals("http://www.auchan.it/", id);
	}

	@Test
	public void buildAddressComponentsCount()
			throws ResponseBuilderParseException {
		builder.openStream(stream);
		int countT = builder.buildAddressComponentsCount();
		Assert.assertEquals(7, countT);
	}

	@Test
	public void buildAddressComponentShortValue()
			throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildAddressComponentShortValue(4);
		Assert.assertEquals("Veneto", id);
	}

	@Test
	public void buildAddressComponentLongValue()
			throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildAddressComponentLongValue(3);
		Assert.assertEquals("Vicenza", id);
	}

	@Test
	public void buildAddressComponentType()
			throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildAddressComponentType(2, 0);
		Assert.assertEquals("locality", id);
		id = builder.buildAddressComponentType(2, 1);
		Assert.assertEquals("political", id);
	}

	@Test
	public void buildFormattedAddress() throws ResponseBuilderParseException {
		builder.openStream(stream);
		String id = builder.buildFormattedAddress();
		Assert.assertEquals("Strada delle Cattane, 71, Vicenza, Italia", id);
	}

}
