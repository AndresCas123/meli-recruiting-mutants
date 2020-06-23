package com.mercadolibre.challenge.core.handler.move;

import com.mercadolibre.challenge.core.model.Orientation;
import com.mercadolibre.challenge.core.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BasicMoveTest {

    private BasicMove basicMove;
    private Position initialPosition;

    @Before
    public void init(){
        final int row = 2;
        final int column = 2;
        initialPosition = new Position( row, column );
        basicMove = new BasicMove();
    }

    @Test
    public void shouldMoveToHorizontal(){
        final int rowExpected = 2;
        final int columnExpected = 3;

        Position positionResponse = basicMove.move( Orientation.HORIZONTAL, initialPosition);
        assertThat( positionResponse, notNullValue() );
        assertThat( positionResponse.getRow(), is( rowExpected ) );
        assertThat( positionResponse.getColumn(), is( columnExpected ) );
    }

    @Test
    public void shouldMoveToSouthEast(){
        final int rowExpected = 3;
        final int columnExpected = 3;

        Position positionResponse = basicMove.move( Orientation.SOUTH_EAST, initialPosition);
        assertThat( positionResponse, notNullValue() );
        assertThat( positionResponse.getRow(), is( rowExpected ) );
        assertThat( positionResponse.getColumn(), is( columnExpected ) );
    }

    @Test
    public void shouldMoveToVertical(){
        final int rowExpected = 3;
        final int columnExpected = 2;

        Position positionResponse = basicMove.move( Orientation.VERTICAL, initialPosition);
        assertThat( positionResponse, notNullValue() );
        assertThat( positionResponse.getRow(), is( rowExpected ) );
        assertThat( positionResponse.getColumn(), is( columnExpected ) );
    }

    @Test
    public void shouldMoveToSouthWest(){
        final int rowExpected = 3;
        final int columnExpected = 1;

        Position positionResponse = basicMove.move( Orientation.SOUTH_WEST, initialPosition);
        assertThat( positionResponse, notNullValue() );
        assertThat( positionResponse.getRow(), is( rowExpected ) );
        assertThat( positionResponse.getColumn(), is( columnExpected ) );
    }
}
