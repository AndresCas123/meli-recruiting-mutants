package com.mercadolibre.challenge.core.handler.move;

import com.mercadolibre.challenge.core.model.Orientation;
import com.mercadolibre.challenge.core.model.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BasicMove implements MoveHandler{

    private final Map<Orientation, Function<Position, Position>> moves;

    public BasicMove() {
        moves = new HashMap<>();
        moves.put( Orientation.VERTICAL, position -> new Position( position.getRow()+1, position.getColumn() ) );
        moves.put( Orientation.HORIZONTAL, position -> new Position( position.getRow(), position.getColumn()+1 ) );
        moves.put( Orientation.SOUTH_EAST, position -> new Position( position.getRow()+1, position.getColumn()+1 ) );
        moves.put( Orientation.SOUTH_WEST, position -> new Position( position.getRow()+1, position.getColumn()-1 ) );
    }

    @Override
    public Position move(Orientation orientation, Position position) {
        return moves.get( orientation ).apply( position );
    }

}
