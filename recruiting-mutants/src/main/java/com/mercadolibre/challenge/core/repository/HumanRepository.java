package com.mercadolibre.challenge.core.repository;

import com.mercadolibre.challenge.core.model.Human;

import java.util.List;

public interface HumanRepository {

    void saveADN( Human human);

    List<Human> searchHumansADN();

}
