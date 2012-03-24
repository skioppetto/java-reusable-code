package com.service.google.places;

import java.io.InputStream;
import java.net.URL;

import com.service.google.places.http.HTTPHandlerImpl;

public class GooPlacesEngine implements IGooPlaceEngine {

	private PlacesEngineUrlBuilder urlBuilder = new PlacesEngineUrlBuilder(
			GooOutputType.xml);
	private IHttpHandler httpHandler = new HTTPHandlerImpl();
	private PlacesEngineResultDirector parser = new PlacesEngineResultDirector();

	public GooPlaceSuggest suggestPlaces(GooSuggestParameters parameters)
			throws PlacesEngineException {
		URL url = urlBuilder.buildPlacesSuggestUrl(parameters);
		InputStream stream = httpHandler.getStream(url);
		return parser.parseSuggest(stream);
	}

	public GooPlaceDetail getDetail(GooDetailParameters parameters)
			throws PlacesEngineException {
		URL url = urlBuilder.buildPlacesDetailUrl(parameters);
		InputStream stream = httpHandler.getStream(url);
		return parser.parseDetailPlace(stream);
	}

	public void setDetailBuilder(IPlaceDetailBuilder builder) {
		parser.setPlaceDetailBuilder(builder);

	}

	public void setSuggestBuilder(IPlaceSuggestBuilder builder) {
		parser.setPlaceSuggestBuilder(builder);

	}

	public void setPlaceDetailFactory(PlaceDetailFactory factory) {
		parser.setDetailFactory(factory);
	}

}
