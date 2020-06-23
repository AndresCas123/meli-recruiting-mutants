package com.mercadolibre.challenge.core.handler.analyzer;

import com.mercadolibre.challenge.core.handler.ExecutorPositionHandler;
import com.mercadolibre.challenge.core.handler.move.MoveHandler;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BasicNucleotideAnalyzer implements NucleotideAnalyzer {

    public static final Function<Future<Boolean>, Boolean> FUTURE_TO_BOOLEAN = convertFutureToBoolean();
    private final MoveHandler moveHandler;

    public BasicNucleotideAnalyzer(MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }

    @Override
    public boolean isMutant(final Nucleotide[][] nucleotides) {
        for( int row=0 ; row < nucleotides.length ; row++ ){
            if( containsRowMutant( row, nucleotides ) )
                return true;
        }
        return false;
    }

    private boolean containsRowMutant(final int row, final Nucleotide[][] nucleotides){
        final List<Future<Boolean>> positionFutures = Collections.synchronizedList( new ArrayList<>());
        IntStream.range(0, nucleotides[row].length).forEach(column -> {
            PositionAnalyzer positionAnalyzer = new PositionAnalyzer( nucleotides, new Position(row, column), moveHandler );
            positionFutures.add( ExecutorPositionHandler.getExecutorService().submit( positionAnalyzer ) );
        });

        return containsRowMutant( positionFutures );
    }

    private boolean containsRowMutant( List<Future<Boolean>> positionFutures ){
        return positionFutures.parallelStream()
                .map( FUTURE_TO_BOOLEAN )
                .filter( aBoolean -> aBoolean )
                .findFirst()
                .orElse( Boolean.FALSE );
    }

    private static Function<Future<Boolean>, Boolean> convertFutureToBoolean() {
        return ( booleanFuture -> {
            try {
                return booleanFuture.get();
            } catch (InterruptedException e) {
                return false;
            } catch (ExecutionException e) {
                return false;
            }
        } );
    }
}
