package com.service.google.places;

import java.io.InputStream;
import java.net.URL;

public interface IHttpHandler {

	public abstract InputStream getStream(URL url);

}