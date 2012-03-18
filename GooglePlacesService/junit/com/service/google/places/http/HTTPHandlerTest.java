package com.service.google.places.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

import com.service.google.places.IHttpHandler;

public class HTTPHandlerTest {

	private String correctUrl = "https://maps.googleapis.com/maps/api/place/search/xml"
			+ "?key=AIzaSyD2WN542isG0Y_o4AXETqmf4Bmue82jl4c"
			+ "&location=-33.8670522,151.1957362"
			+ "&radius=1000"
			+ "&sensor=false";
	IHttpHandler testing = new HTTPHandlerImpl();

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
