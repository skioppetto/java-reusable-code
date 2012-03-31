package com.service.google.places.model;

public interface PoliticalRelashionshipTree {

	public static final GooPlacesType[] tree = new GooPlacesType[]{
		GooPlacesType.country,
		GooPlacesType.administrative_area_level_1,
		GooPlacesType.administrative_area_level_2,
		GooPlacesType.administrative_area_level_3,
		GooPlacesType.locality,
		GooPlacesType.sublocality,
		GooPlacesType.sublocality_level_1,
		GooPlacesType.sublocality_level_2,
		GooPlacesType.sublocality_level_3,
		GooPlacesType.sublocality_level_4,
		GooPlacesType.sublocality_level_5,
		GooPlacesType.route,
		GooPlacesType.street_address,
	};
	
	
	
}
