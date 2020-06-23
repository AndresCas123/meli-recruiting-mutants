package com.mercadolibre.challenge.core.service;

import com.mercadolibre.challenge.core.model.Human;
import com.mercadolibre.challenge.core.model.Stats;
import com.mercadolibre.challenge.core.repository.HumanRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsService {

    private final HumanRepository humanRepository;

    public StatsService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public Stats createStats(){
        List<Human> humans = humanRepository.searchHumansADN();
        AtomicInteger mutantsTotal = new AtomicInteger();
        AtomicInteger humansTotal = new AtomicInteger();
        humans.stream().forEach( human -> { if (human.isMutant()) mutantsTotal.getAndIncrement(); else humansTotal.getAndIncrement(); } );
        return new Stats( mutantsTotal.get(), humansTotal.get() );
    }

}
