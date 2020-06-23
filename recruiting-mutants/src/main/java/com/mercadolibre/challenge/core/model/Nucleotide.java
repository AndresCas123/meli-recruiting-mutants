package com.mercadolibre.challenge.core.model;

import com.mercadolibre.challenge.core.exception.DNAException;

import java.util.Arrays;

public enum Nucleotide {
    A, T, G, C;

    public static Nucleotide value( String nucleotideMatrix ) throws DNAException {
        return Arrays.stream( Nucleotide.values() )
                .filter( nucleotide -> nucleotide.name().equals( nucleotideMatrix ) )
                .findFirst().orElseThrow( () ->  new DNAException("Nucleotide not identified: " + nucleotideMatrix)  );
    }
}