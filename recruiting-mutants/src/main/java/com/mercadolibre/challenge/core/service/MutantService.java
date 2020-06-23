package com.mercadolibre.challenge.core.service;

import com.mercadolibre.challenge.core.exception.DNAException;
import com.mercadolibre.challenge.core.handler.analyzer.NucleotideAnalyzer;
import com.mercadolibre.challenge.core.model.Human;
import com.mercadolibre.challenge.core.repository.HumanRepository;

public class MutantService {

    private final NucleotideAnalyzer nucleotideAnalyzer;
    private final HumanRepository humanRepository;

    public MutantService(NucleotideAnalyzer nucleotideAnalyzer, HumanRepository humanRepository) {
        this.nucleotideAnalyzer = nucleotideAnalyzer;
        this.humanRepository = humanRepository;
    }

    public boolean isMutant(String[][] sequences ) throws DNAException {
        Human human = new Human( sequences );
        human.processNucleotides( nucleotideAnalyzer );
        humanRepository.saveADN( human );
        if( !human.isMutant() )
            throw new DNAException("DNA is human");

        return human.isMutant();
    }

}
