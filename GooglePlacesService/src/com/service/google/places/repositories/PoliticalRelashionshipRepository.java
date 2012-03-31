package com.service.google.places.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.service.google.places.model.PoliticalRelashionship;

@Repository
public interface PoliticalRelashionshipRepository extends
		GraphRepository<PoliticalRelashionship> {

}
