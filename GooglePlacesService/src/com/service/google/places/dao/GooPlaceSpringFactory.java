package com.service.google.places.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.service.google.places.builder.PlaceDetailFactory;
import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.repositories.GooAddressItemRepository;
import com.service.google.places.repositories.PlaceDetailRepository;

@Component
public class GooPlaceSpringFactory extends PlaceDetailFactory {

	@Autowired
	private GooAddressItemRepository itemRepo;
	@Autowired
	private PlaceDetailRepository placeRepo;

	@Override
	@Transactional
	public GooPlaceDetail createGooPlaceDetail(String key) {
		GooPlaceDetail place = placeRepo.findByPropertyValue("uid", key);
		if (place == null) {
			place = new GooPlaceDetail();
			place.setUid(key);
		}
		return place;
	}

	@Override
	public GooAddress createGooPlaceAddress() {
		return new GooAddress();
	}

	@Override
	@Transactional
	public GooAddressItem createGooAddressItem(String key) {
		GooAddressItem item = itemRepo.findByPropertyValue("value", key);
		if (item == null) {
			item = new GooAddressItem();
			item.setValue(key);
		}
		return item;
	}

}
