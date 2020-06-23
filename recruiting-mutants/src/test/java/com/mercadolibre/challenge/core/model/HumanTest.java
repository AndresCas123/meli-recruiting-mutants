package com.mercadolibre.challenge.core.model;

import com.mercadolibre.challenge.core.exception.DNAException;
import com.mercadolibre.challenge.core.handler.analyzer.NucleotideAnalyzer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.mercadolibre.challenge.core.model.Nucleotide.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HumanTest {

    @Mock private NucleotideAnalyzer analyzer;

    @Test
    public void shouldCreateHumanSuccessfully() throws DNAException {
        final String[][] sequences = {
                {"A", "T", "C", "G"},
                {"A", "T", "C", "G"},
                {"T", "C", "G", "A"},
                {"T", "C", "G", "A"}
        };

        Human human = new Human( sequences );
        assertThat( human, notNullValue());
        assertThat( human.getNucleotides(), notNullValue());
        assertThat( human.isMutant(), is( FALSE ));
    }

    @Test
    public void shouldCreateHumanSuccessfullyFromDatabaseInformation() throws DNAException {
        final Boolean isMutant = TRUE;
        final Nucleotide[][] nucleotides = {
                {A, T, C, G},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };

        Human human = new Human( nucleotides, isMutant );
        assertThat( human, notNullValue());
        assertThat( human.getNucleotides(), is(nucleotides));
        assertThat( human.isMutant(), is( isMutant ));
    }

    @Test( expected = DNAException.class)
    public void shouldThrowDNAExceptionWhenDNAIsInvalid() throws DNAException {
        new Human( null );
    }

    @Test( expected = DNAException.class)
    public void shouldThrowDNAExceptionWhenDNAIsNotMatrix() throws DNAException {
        final String[][] sequences = {
                {"A", "T", "C", "G"},
                {"A", "T", "C", "G"}
        };

        new Human( sequences );
    }

    @Test
    public void shouldReturnFalseWhenDNAIsHuman() throws DNAException {
        final String[][] sequences = {
                {"A", "T", "C", "G"},
                {"A", "T", "C", "G"},
                {"T", "C", "G", "A"},
                {"T", "C", "G", "A"}
        };

        Human human = new Human( sequences );
        Mockito.when( analyzer.isMutant( human.getNucleotides() ) ).thenReturn( FALSE );

        boolean response = human.processNucleotides( analyzer );
        assertThat( response, is( FALSE ));
    }

    @Test
    public void shouldReturnTrueWhenDNAIsMutantValid() throws DNAException {
        final String[][] sequences = {
                {"A", "A", "A", "A"},
                {"A", "T", "C", "G"},
                {"T", "C", "G", "A"},
                {"T", "C", "G", "A"}
        };

        Human human = new Human( sequences );
        Mockito.when( analyzer.isMutant( human.getNucleotides() ) ).thenReturn( Boolean.TRUE );

        boolean response = human.processNucleotides( analyzer );
        assertThat( response, is( TRUE ));

        Mockito.verify( analyzer ).isMutant( human.getNucleotides() );
    }

}
