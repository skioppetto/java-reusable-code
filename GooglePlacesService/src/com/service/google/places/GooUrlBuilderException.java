package com.service.google.places;

import java.net.MalformedURLException;

public class GooUrlBuilderException extends GooPlacesException {

	public GooUrlBuilderException(MalformedURLException e) {
		super(e);
	}

	public GooUrlBuilderException() {
	}

	private static final long serialVersionUID = -7182653739839856761L;

}
