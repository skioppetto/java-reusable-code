package com.service.google.places.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/GooglePlaceService-context.xml" })
public class HTTPHandlerTest {

	private String correctUrl = "https://maps.googleapis.com/maps/api/place/search/xml"
			+ "?key=AIzaSyD2WN542isG0Y_o4AXETqmf4Bmue82jl4c"
			+ "&location=-33.8670522,151.1957362"
			+ "&radius=1000"
			+ "&sensor=false";
	@Autowired
	IHttpHandler testing;

	@Test
	public void testGetStream() throws IOException {
		URL url = new URL(correctUrl);
		InputStream stream = testing.getStream(url);
		Assert.assertNotNull(stream);
		byte[] buff = new byte[100];
		while (stream.read(buff) > 0){
			Assert.assertTrue(buff.length > 0);
			System.out.println(new String(buff));
		}
	}
}
