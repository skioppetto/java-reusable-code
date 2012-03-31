package com.service.google.places.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;


@Component
public class HTTPHandlerImpl implements IHttpHandler {

	URLConnection connection = null;

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
