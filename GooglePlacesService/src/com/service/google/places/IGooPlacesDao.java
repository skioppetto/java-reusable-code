package com.service.google.places;

public interface IGooPlacesDao {

	void save(GooPlaceDetail detail);

	GooPlaceDetail getByUid(String uid);

}
