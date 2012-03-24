package com.service.google.places;

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

	public GooPlaceDetail getDetail(GooPlaceCachedParameters parameters)
			throws PlacesEngineException {
		GooPlaceDetail detail = dao.getByUid(parameters.getUid());
		if (detail == null){
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
