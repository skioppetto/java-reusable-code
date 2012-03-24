package com.service.google.places;

public interface IGooPlaceEngine {

	public abstract GooPlaceSuggest suggestPlaces(
			GooSuggestParameters parameters) throws PlacesEngineException;

	public abstract GooPlaceDetail getDetail(GooDetailParameters parameters)
			throws PlacesEngineException;

	void setPlaceDetailFactory(PlaceDetailFactory factory);

}