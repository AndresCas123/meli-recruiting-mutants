package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.handler.move.BasicMove;
import com.mercadolibre.challenge.core.model.Nucleotide;
import org.junit.Before;
import org.junit.Test;

import static com.mercadolibre.challenge.core.model.Nucleotide.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BasicNucleotideAnalyzerTest {

    private BasicMove basicMove;
    private BasicNucleotideAnalyzer basicNucleotideAnalyzer;

    @Before
    public void init(){
        basicMove = new BasicMove();
        basicNucleotideAnalyzer = new BasicNucleotideAnalyzer(basicMove);
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutant(){
        final Nucleotide[][] nucleotides = {
                {A, A, A, A},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };

        boolean response = basicNucleotideAnalyzer.isMutant( nucleotides );
        assertThat( response, is( TRUE ));
    }

    @Test
    public void shouldReturnHumanExceptionWhenDNAIsHuman(){
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };

        boolean response = basicNucleotideAnalyzer.isMutant( nucleotides );
        assertThat( response, is( FALSE ));
    }

}
