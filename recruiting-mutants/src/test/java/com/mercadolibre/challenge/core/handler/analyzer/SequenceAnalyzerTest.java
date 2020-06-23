package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.handler.move.BasicMove;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.model.Orientation;
import com.mercadolibre.challenge.core.model.Position;
import org.junit.Before;
import org.junit.Test;

import static com.mercadolibre.challenge.core.model.Nucleotide.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SequenceAnalyzerTest {

    private BasicMove basicMove;
    private SequenceAnalyzer sequenceAnalyzer;

    @Before
    public void init(){
        basicMove = new BasicMove();
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutantAndOrientationIsHorizontal(){
        final Orientation orientation = Orientation.HORIZONTAL;
        final Nucleotide nucleotide = A;
        final Position position = new Position(0,0);
        final Nucleotide[][] nucleotides = {
                {A, A, A, A},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        } ;

        sequenceAnalyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, basicMove );
        boolean response = sequenceAnalyzer.call();
        assertThat( response, is( TRUE ));
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutantAndOrientationIsVertical(){
        final Orientation orientation = Orientation.VERTICAL;
        final Nucleotide nucleotide = A;
        final Position position = new Position(0,0);
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, T, C, G},
                {A, C, G, A},
                {A, C, G, A}
        } ;

        sequenceAnalyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, basicMove );
        boolean response = sequenceAnalyzer.call();
        assertThat( response, is( TRUE ));
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutantAndOrientationIsSouthEast(){
        final Orientation orientation = Orientation.SOUTH_EAST;
        final Nucleotide nucleotide = A;
        final Position position = new Position(0,0);
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, A, C, G},
                {T, C, A, A},
                {T, C, G, A}
        } ;

        sequenceAnalyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, basicMove );
        boolean response = sequenceAnalyzer.call();
        assertThat( response, is( TRUE ));
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutantAndOrientationIsSouthWest(){
        final Orientation orientation = Orientation.SOUTH_WEST;
        final Nucleotide nucleotide = G;
        final Position position = new Position(0,3);
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, T, G, G},
                {T, G, A, A},
                {G, C, G, A}
        } ;

        sequenceAnalyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, basicMove );
        boolean response = sequenceAnalyzer.call();
        assertThat( response, is( TRUE ));
    }

    @Test
    public void shouldReturnFalseWhenDNAIsMutantAndMoveOnIsInvalid(){
        final Orientation orientation = Orientation.VERTICAL;
        final Nucleotide nucleotide = A;
        final Position position = new Position(3,0);
        final Nucleotide[][] nucleotides = {
                {A, A, A, A},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        } ;

        sequenceAnalyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, basicMove );
        boolean response = sequenceAnalyzer.call();
        assertThat( response, is( FALSE ));
    }

    @Test
    public void shouldReturnFalseWhenDNAIsHuman(){
        final Orientation orientation = Orientation.VERTICAL;
        final Nucleotide nucleotide = A;
        final Position position = new Position(0,0);
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };

        sequenceAnalyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, basicMove );
        boolean response = sequenceAnalyzer.call();
        assertThat( response, is( FALSE ));
    }

}
