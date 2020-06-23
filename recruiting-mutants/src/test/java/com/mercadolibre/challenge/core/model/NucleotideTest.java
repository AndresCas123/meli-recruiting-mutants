package com.mercadolibre.challenge.core.model;

import com.mercadolibre.challenge.core.exception.DNAException;
import org.junit.Test;

import static com.mercadolibre.challenge.core.model.Nucleotide.A;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NucleotideTest {

    @Test
    public void shouldReturnNucleotideA() throws DNAException {
        assertThat( Nucleotide.value( "A" ), is(A));
    }

    @Test(expected = DNAException.class)
    public void shouldReturnExceptionWhenValueIsInvalid() throws DNAException {
        Nucleotide.value( "X" );
    }
}
