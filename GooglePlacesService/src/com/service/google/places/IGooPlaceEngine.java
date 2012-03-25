package com.service.google.places;

import com.service.google.places.builder.PlaceDetailFactory;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.request.GooDetailParameters;
import com.service.google.places.request.GooSuggestParameters;

public interface IGooPlaceEngine {

	public abstract GooPlaceSuggest suggestPlaces(
			GooSuggestParameters parameters) throws PlacesEngineException;

	public abstract GooPlaceDetail getDetail(GooDetailParameters parameters)
			throws PlacesEngineException;

	public String suggestPlacesJson(GooSuggestParameters parameters)
			throws PlacesEngineException;

	public String suggestPlacesXml(GooSuggestParameters parameters)
			throws PlacesEngineException;

	void setPlaceDetailFactory(PlaceDetailFactory factory);

	public String getDetailJson(GooDetailParameters parameters)
			throws PlacesEngineException;

	public String getDetailXml(GooDetailParameters parameters)
			throws PlacesEngineException;

}