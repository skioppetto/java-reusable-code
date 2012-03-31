package com.service.google.places.dao;

import org.springframework.transaction.annotation.Transactional;

import com.service.google.places.model.GooPlaceDetail;

public interface IGooPlacesDao {

	@Transactional
	void save(GooPlaceDetail detail);

	@Transactional
	GooPlaceDetail getByUid(String uid);

}
