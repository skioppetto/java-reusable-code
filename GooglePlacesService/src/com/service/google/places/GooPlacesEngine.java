package com.service.google.places;

import java.io.InputStream;
import java.net.URL;

import com.service.google.places.http.HTTPHandlerImpl;

public class GooPlacesEngine {

	private GooPlacesUrlBuilder urlBuilder = new GooPlacesUrlBuilder(
			OutputType.xml);
	private IHttpHandler httpHandler = new HTTPHandlerImpl();
	private GooPlacesResultDirector parser = new GooPlacesResultDirector();

	public GooPlaceSuggest suggestPlaces(SuggestParameters parameters)
			throws GooPlacesException {

		URL url = urlBuilder.buildPlacesSuggestUrl(parameters);
		InputStream stream = httpHandler.getStream(url);
		return parser.parseSuggest(stream);

	}

	public GooPlaceDetail getDetail(String uid) throws GooPlacesException {
		URL url = urlBuilder.buildPlacesDetailUrl(uid);
		InputStream stream = httpHandler.getStream(url);
		return parser.parseDetailPlace(stream);

	}

}
