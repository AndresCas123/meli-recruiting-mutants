package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.model.Nucleotide;

public interface NucleotideAnalyzer {

    boolean isMutant(Nucleotide[][] nucleotides);

}
