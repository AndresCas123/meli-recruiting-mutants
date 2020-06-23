package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.handler.move.MoveHandler;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.model.Orientation;
import com.mercadolibre.challenge.core.model.Position;

import java.util.concurrent.Callable;

public class SequenceAnalyzer implements Callable<Boolean> {

    private static final int MAX_SEQUENCE_MUTANT = 4;

    private final Orientation orientation;
    private final Nucleotide nucleotide;
    private final Nucleotide[][] nucleotides;
    private final Position position;
    private final MoveHandler moveHandler;
    private final int times;

    public SequenceAnalyzer(Orientation orientation, Nucleotide nucleotide, Nucleotide[][] nucleotides, Position position, MoveHandler moveHandler) {
        this.orientation = orientation;
        this.nucleotide = nucleotide;
        this.nucleotides = nucleotides;
        this.position = position;
        this.moveHandler = moveHandler;
        this.times = 1;
    }

    @Override
    public Boolean call() {
        try{
            return isSequenceMutant( orientation, nucleotide, nucleotides, position, times );
        }catch ( ArrayIndexOutOfBoundsException exception ){
            return false;
        }

    }

    private boolean isSequenceMutant(final Orientation orientation, final Nucleotide nucleotide, final Nucleotide[][] nucleotides,final Position initialPosition, int times){
        times++;
        Position newPosition = moveHandler.move( orientation, initialPosition );
        Nucleotide nucleotideMatrix = nucleotides[ newPosition.getRow() ][ newPosition.getColumn() ];

        if(nucleotideMatrix == nucleotide){
            return times == MAX_SEQUENCE_MUTANT || isSequenceMutant( orientation, nucleotide, nucleotides, newPosition, times );
        }
        return false;
    }

}
