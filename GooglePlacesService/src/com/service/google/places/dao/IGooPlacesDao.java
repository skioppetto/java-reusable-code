package com.service.google.places.dao;

import com.service.google.places.model.GooPlaceDetail;

public interface IGooPlacesDao {

	void save(GooPlaceDetail detail);

	GooPlaceDetail getByUid(String uid);

}
