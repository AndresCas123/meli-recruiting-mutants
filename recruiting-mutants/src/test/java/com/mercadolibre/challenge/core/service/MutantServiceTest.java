package com.mercadolibre.challenge.core.service;

import com.mercadolibre.challenge.core.exception.DNAException;
import com.mercadolibre.challenge.core.handler.analyzer.NucleotideAnalyzer;
import com.mercadolibre.challenge.core.model.Human;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.repository.HumanRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    @Mock private NucleotideAnalyzer nucleotideAnalyzer;
    @Mock private HumanRepository humanRepository;

    private MutantService mutantService;

    @Before
    public void init(){
        mutantService = new MutantService( nucleotideAnalyzer, humanRepository );
    }

    @Test
    public void shouldReturnTrueAndSaveDNA() throws DNAException {
        final String[][] sequences = {
                {"A", "A", "A", "A"},
                {"A", "T", "C", "G"},
                {"T", "C", "G", "A"},
                {"T", "C", "G", "A"}
        };

        Mockito.when( nucleotideAnalyzer.isMutant( any(Nucleotide[][].class) ) ).thenReturn(TRUE);
        Mockito.doNothing().when( humanRepository ).saveADN( any(Human.class) );

        boolean response = mutantService.isMutant( sequences );
        assertThat( response, is(TRUE));

        Mockito.verify( nucleotideAnalyzer ).isMutant( any(Nucleotide[][].class) );
        Mockito.verify( humanRepository ).saveADN( any(Human.class) );
    }

    @Test
    public void shouldReturnFalseAndSaveDNAWhenDNAIsHuman() {
        final String[][] sequences = {
                {"A", "T", "C", "G"},
                {"A", "T", "C", "G"},
                {"T", "C", "G", "A"},
                {"T", "C", "G", "A"}
        };

        Mockito.when( nucleotideAnalyzer.isMutant( any(Nucleotide[][].class) ) ).thenReturn(FALSE);
        Mockito.doNothing().when( humanRepository ).saveADN( any(Human.class) );

        try {
            mutantService.isMutant( sequences );
            Assert.fail();
        } catch (DNAException e) {
            assertThat( e, instanceOf(DNAException.class) );

            Mockito.verify( nucleotideAnalyzer ).isMutant( any(Nucleotide[][].class) );
            Mockito.verify( humanRepository ).saveADN( any(Human.class) );
        }
    }

    @Test(expected = DNAException.class)
    public void shouldThrowDNAExceptionNWhenDNAIsInvalid() throws DNAException {
        final String[][] sequences = {
                {"X", "T", "C", "G"},
                {"A", "T", "C", "G"},
                {"T", "C", "G", "A"},
                {"T", "C", "G", "A"}
        };

        mutantService.isMutant( sequences );
    }
}
