package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.handler.move.BasicMove;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.model.Position;
import org.junit.Before;
import org.junit.Test;

import static com.mercadolibre.challenge.core.model.Nucleotide.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PositionAnalyzerTest {

    private BasicMove basicMove;
    private PositionAnalyzer positionAnalyzer;

    @Before
    public void init(){
        basicMove = new BasicMove();
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutant(){
        final Position position = new Position(0,0);
        final Nucleotide[][] nucleotides = {
                {A, A, A, A},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };

        positionAnalyzer = new PositionAnalyzer( nucleotides, position, basicMove );
        boolean response = positionAnalyzer.call();
        assertThat( response, is( TRUE ));
    }

    @Test
    public void shouldReturnFalseWhenDNAIsHuman(){
        final Position position = new Position(0,0);
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };

        positionAnalyzer = new PositionAnalyzer( nucleotides, position, basicMove );
        boolean response = positionAnalyzer.call();
        assertThat( response, is( FALSE ));
    }

}
