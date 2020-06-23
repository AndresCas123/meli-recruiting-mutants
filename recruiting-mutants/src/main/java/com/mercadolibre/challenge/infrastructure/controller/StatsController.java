package com.mercadolibre.challenge.infrastructure.controller;

import com.mercadolibre.challenge.core.model.Stats;
import com.mercadolibre.challenge.core.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("stats")
    public Stats getStats(){
        return statsService.createStats();
    }

}
