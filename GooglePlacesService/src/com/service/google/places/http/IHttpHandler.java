package com.service.google.places.http;

import java.io.InputStream;
import java.net.URL;

public interface IHttpHandler {

	InputStream getStream(URL url);

}