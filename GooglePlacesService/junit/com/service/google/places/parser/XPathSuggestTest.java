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

import com.service.google.places.builder.IPlaceSuggestBuilder;
import com.service.google.places.builder.ResponseBuilderParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/GooglePlaceService-context.xml" })
public class XPathSuggestTest {

	@Autowired
	private IPlaceSuggestBuilder builder ;
	private InputStream stream;

	@Before
	public void loadTestStream() {
		stream = XPathSuggestTest.class
				.getResourceAsStream("GooglePlaceSearchResponse.xml");
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
	public void buildResultsCount() throws ResponseBuilderParseException {
		builder.openStream(stream);
		int countR = builder.buildResultsCount();
		Assert.assertEquals(12, countR);
	}

	@Test
	public void buildVicinity() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		String vicinity = builder.buildVicinity();
		Assert.assertEquals("Vicenza", vicinity);
		builder.setItemId(1);
		vicinity = builder.buildVicinity();
		Assert.assertEquals("Contrà Lodi, 31, Vicenza", vicinity);
		
	}

	@Test
	public void buildGeometryLatitude() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		double value = builder.buildGeometryLatitude();
		Assert.assertEquals(45.5486452, value);
		builder.setItemId(2);
		value = builder.buildGeometryLatitude();
		Assert.assertEquals(45.5487360, value);
	}

	@Test
	public void buildGeometryLongitude() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		double value = builder.buildGeometryLongitude();
		Assert.assertEquals(11.5392852, value);
		builder.setItemId(2);
		value = builder.buildGeometryLongitude();
		Assert.assertEquals(11.5393970, value);
	}

	@Test
	public void buildIcon() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		String icon = builder.buildIcon();
		Assert.assertEquals("http://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png", icon);
		builder.setItemId(1);
		icon = builder.buildIcon();
		Assert.assertEquals("http://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png", icon);
	}

	@Test
	public void buildName() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		String name = builder.buildName();
		Assert.assertEquals("Contrà Lodi", name);
		builder.setItemId(1);
		name = builder.buildName();
		Assert.assertEquals("Studio Rebecca & Associati", name);
	}

	@Test
	public void buildRating() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		Double value = builder.buildRating();
		Assert.assertEquals(null, value);
		builder.setItemId(2);
		value = builder.buildRating();
		Assert.assertEquals(null, value);
	}

	@Test
	public void buildReference() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		String refer = builder.buildReference();
		Assert.assertEquals("CnRtAAAA-IshlOHCZPbUmWQ4JrvtYLSMowuan-MoBs8NRuIjB-6z17LC48BH037kvlJP5p5HpRxYMuLFFNpnRmpIND_nB1mrv4iV8NlGTi95suYM096nX4UtvMKJGHu8rWxzZEMMFyTC9ich9zN5GdDCsiJBSBIQ0xq3Kukxo_5nhCsjCUtqYBoUS_rFrAWbT2C6BIOfM1pP86tPi-g", refer);
		builder.setItemId(1);
		refer = builder.buildReference();
		Assert.assertEquals("CoQBcQAAAAZn470KsJR0UY2Dek8SIqf2egiVFzG_vFBp_qpaoWkKKQQUi46hoHwxMRnnU_s8S2wvukpn3ywSKqAbHb-x-Vh3ZdctlNjiwop6rT70G3IyOUCtXlVwzprlibdNEyqSjOnUXIvEey9H5dh_6HwC4BWMdoz2cpFj2qBpdrNtpqeaEhDsnp806GovPFP3A16s-GwWGhR23t_4LRYuFfbgIpASl0nf2rZPAw", refer);
	}

	@Test
	public void buildType() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		String type = builder.buildType(0);
		Assert.assertEquals("route", type);
		builder.setItemId(1);
		type = builder.buildType(2);
		Assert.assertEquals("establishment", type);
	}

	@Test
	public void buildTypesCount() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(1);
		int countT =  builder.buildTypesCount();
		Assert.assertEquals(3, countT);
	}

	@Test
	public void buildId() throws ResponseBuilderParseException {
		builder.openStream(stream);
		builder.setItemId(0);
		String id = builder.buildId();
		Assert.assertEquals("3aa9db85e25183be1a03754124539580ecad8148", id);
		builder.setItemId(1);
		id = builder.buildId();
		Assert.assertEquals("c3a2119aaf8a1270e774ddc4f06901e49bf605be", id);
	}

}
