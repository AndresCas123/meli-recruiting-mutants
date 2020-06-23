package com.mercadolibre.challenge.core.handler.move;

import com.mercadolibre.challenge.core.model.Orientation;
import com.mercadolibre.challenge.core.model.Position;

public interface MoveHandler {

    Position move(Orientation orientation, Position position );

}
