package com.service.google.places;

import org.springframework.transaction.annotation.Transactional;

import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.request.GooPlaceCachedParameters;


public interface IGooPlaceCachedEngine extends IGooPlaceEngine {

	@Transactional
	GooPlaceDetail getOrCacheDetail(GooPlaceCachedParameters parameters)
			throws PlacesEngineException;

	@Transactional
	GooPlaceDetail getByUid(String uid);

}
