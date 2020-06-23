package com.mercadolibre.challenge.core.model;

import com.mercadolibre.challenge.core.exception.DNAException;
import com.mercadolibre.challenge.core.handler.analyzer.NucleotideAnalyzer;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Human {

    private final Nucleotide[][] nucleotides;
    private boolean mutant;

    public Human(String[][] sequences) throws DNAException {
        this.nucleotides = createNucleotides( sequences );
    }

    public Human(Nucleotide[][] nucleotides, boolean mutant) {
        this.nucleotides = nucleotides;
        this.mutant = mutant;
    }

    public boolean processNucleotides( NucleotideAnalyzer analyzer ) {
        mutant = analyzer.isMutant( nucleotides );
        return mutant;
    }

    private Nucleotide[][] createNucleotides( String[][] sequences ) throws DNAException {
        validateMatrix( sequences );
        Nucleotide[][] nucleotides = new Nucleotide[sequences.length][sequences[0].length];
        for( int row=0 ; row < sequences.length ; row++ ){
            for( int column=0 ; column < sequences.length ; column++ ){
                nucleotides[row][column] = Nucleotide.value( sequences[row][column] );
            }
        }
        return nucleotides;
    }

    private void validateMatrix( String[][] sequences ) throws DNAException{
        if(Objects.isNull( sequences ) )
            throw new DNAException("Invalid DNA");

        final int rows = sequences.length;
        for( int row=0 ; row < rows ; row++ ){
            if( rows != sequences[row].length )
                throw new DNAException("DNA is not a matrix");
        }
    }
}
