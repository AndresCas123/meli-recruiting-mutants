package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.handler.ExecutorSequenceHandler;
import com.mercadolibre.challenge.core.handler.move.MoveHandler;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.model.Orientation;
import com.mercadolibre.challenge.core.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static com.mercadolibre.challenge.core.handler.analyzer.BasicNucleotideAnalyzer.FUTURE_TO_BOOLEAN;

public class PositionAnalyzer implements Callable<Boolean> {

    private final Nucleotide[][] nucleotides;
    private final Position position;
    private final MoveHandler moveHandler;

    public PositionAnalyzer(Nucleotide[][] nucleotides, Position position, MoveHandler moveHandler) {
        this.nucleotides = nucleotides;
        this.position = position;
        this.moveHandler = moveHandler;
    }

    @Override
    public Boolean call() {
        return isSequenceMutant( position.getRow(), position.getColumn(), nucleotides );
    }

    private boolean isSequenceMutant(final int row, final int column, final Nucleotide[][] nucleotides ) {
        final List<Future<Boolean>> futures = new ArrayList<>();
        final Position position = new Position(row, column);
        final Nucleotide nucleotide = nucleotides[row][column];

        Arrays.stream( Orientation.values() ).forEach(orientation -> {
            Callable analyzer = new SequenceAnalyzer( orientation, nucleotide, nucleotides, position, moveHandler );
            futures.add( ExecutorSequenceHandler.getExecutorService().submit( analyzer ) );
        } );

        return futures.parallelStream()
                .map( FUTURE_TO_BOOLEAN )
                .filter( aBoolean -> aBoolean )
                .findFirst()
                .orElse( Boolean.FALSE );
    }

}
