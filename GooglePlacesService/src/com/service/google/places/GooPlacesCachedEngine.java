package com.service.google.places;

import com.service.google.places.builder.PlaceDetailFactory;
import com.service.google.places.dao.IGooPlacesDao;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.request.GooDetailParameters;
import com.service.google.places.request.GooPlaceCachedParameters;
import com.service.google.places.request.GooSuggestParameters;

public class GooPlacesCachedEngine implements IGooPlaceEngine {

	private IGooPlacesDao dao;
	private IGooPlaceEngine engine;

	public GooPlacesCachedEngine(IGooPlaceEngine engine, IGooPlacesDao dao) {
		this.dao = dao;
		this.engine = engine;
	}

	public GooPlaceSuggest suggestPlaces(GooSuggestParameters parameters)
			throws PlacesEngineException {
		return engine.suggestPlaces(parameters);
	}

	public GooPlaceDetail getCachedDetail(GooPlaceCachedParameters parameters)
			throws PlacesEngineException {
		GooPlaceDetail detail = dao.getByUid(parameters.getUid());
		if (detail == null) {
			return getDetail((GooDetailParameters) parameters);
		}
		return detail;
	}

	public GooPlaceDetail getDetail(GooDetailParameters parameters)
			throws PlacesEngineException {
		GooPlaceDetail detail = engine.getDetail(parameters);
		getDao().save(detail);
		return detail;
	}

	public String suggestPlacesJson(GooSuggestParameters parameters)
			throws PlacesEngineException {
		return engine.suggestPlacesJson(parameters);
	}

	public String suggestPlacesXml(GooSuggestParameters parameters)
			throws PlacesEngineException {
		return engine.suggestPlacesXml(parameters);
	}

	public String getDetailJson(GooDetailParameters parameters)
			throws PlacesEngineException {
		return engine.getDetailJson(parameters);
	}

	public String getDetailXml(GooDetailParameters parameters)
			throws PlacesEngineException {
		return engine.getDetailXml(parameters);
	}

	public void setPlaceDetailFactory(PlaceDetailFactory factory) {
		engine.setPlaceDetailFactory(factory);
	}

	public IGooPlacesDao getDao() {
		return dao;
	}

	public void setDao(IGooPlacesDao dao) {
		this.dao = dao;
	}

	public GooPlaceDetail getByUid(String uid) {
		return dao.getByUid(uid);
	}

}
