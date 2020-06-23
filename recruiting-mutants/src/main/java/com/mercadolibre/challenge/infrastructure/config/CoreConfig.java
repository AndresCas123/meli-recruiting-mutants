package com.mercadolibre.challenge.infrastructure.config;

import com.mercadolibre.challenge.core.handler.analyzer.BasicNucleotideAnalyzer;
import com.mercadolibre.challenge.core.handler.analyzer.NucleotideAnalyzer;
import com.mercadolibre.challenge.core.handler.move.BasicMove;
import com.mercadolibre.challenge.core.handler.move.MoveHandler;
import com.mercadolibre.challenge.core.repository.HumanRepository;
import com.mercadolibre.challenge.core.service.MutantService;
import com.mercadolibre.challenge.core.service.StatsService;
import com.mercadolibre.challenge.infrastructure.repository.HumanRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    private final HumanRepository humanRepository;

    @Autowired
    public CoreConfig(HumanRepositoryImpl humanRepositoryImpl) {
        this.humanRepository = humanRepositoryImpl;
    }

    @Bean
    public MoveHandler buildMoveBasicHandler(){
        return new BasicMove();
    }

    @Bean
    public NucleotideAnalyzer buildBasicNucleotideAnalyzer(){
        return new BasicNucleotideAnalyzer( buildMoveBasicHandler() );
    }

    @Bean
    public MutantService buildMutantManager(){
        return new MutantService( buildBasicNucleotideAnalyzer(), humanRepository );
    }

    @Bean
    public StatsService buildStatsManager(){
        return new StatsService( humanRepository );
    }

}
