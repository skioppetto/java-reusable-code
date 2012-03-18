package com.service.google.places;

import java.net.MalformedURLException;

public class PlacesEngineUrlBuilderException extends PlacesEngineException {

	public PlacesEngineUrlBuilderException(MalformedURLException e) {
		super(e);
	}

	public PlacesEngineUrlBuilderException() {
	}

	private static final long serialVersionUID = -7182653739839856761L;

}
