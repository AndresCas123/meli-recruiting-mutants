package com.mercadolibre.challenge.core.model;

import lombok.Getter;

@Getter
public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
