package com.service.google.places.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.service.google.places.IHttpHandler;

public class HTTPHandlerImpl implements IHttpHandler {

	URLConnection connection = null;

	@Override
	public InputStream getStream(URL url) {
		try {
			connection = url.openConnection();
			return connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
