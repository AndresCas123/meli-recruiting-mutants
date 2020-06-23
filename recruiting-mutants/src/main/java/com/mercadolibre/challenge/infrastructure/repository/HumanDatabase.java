package com.mercadolibre.challenge.infrastructure.repository;

import com.mercadolibre.challenge.infrastructure.repository.entity.HumanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanDatabase extends CrudRepository<HumanEntity, String> {

}
