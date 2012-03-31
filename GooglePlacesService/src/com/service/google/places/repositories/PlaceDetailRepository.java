package com.service.google.places.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.service.google.places.model.GooPlaceDetail;

@Repository
public interface PlaceDetailRepository extends GraphRepository<GooPlaceDetail>{

	GooPlaceDetail findByUid(String uid);

}
