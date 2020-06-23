package com.mercadolibre.challenge.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hibernate.type.DoubleType.ZERO;

public class StatsTest {

    @Test
    public void shouldReturnRatioZeroWhenHumansTotalIsZero(){
        final double mutantsTotal = 1;
        final double humansTotal = 0;
        final double ratioExpected = ZERO;
        Stats stats = new Stats( mutantsTotal, humansTotal );
        assertThat( stats, notNullValue());
        assertThat( stats.getMutantsTotal(), is(mutantsTotal));
        assertThat( stats.getHumansTotal(), is(humansTotal));
        assertThat( stats.getRatio(), is(ratioExpected));
    }

    @Test
    public void shouldReturnRatioOneWhenHumansTotalAndMutantsAreOne(){
        final double mutantsTotal = 1;
        final double humansTotal = 1;
        final double ratioExpected = 1;
        Stats stats = new Stats( mutantsTotal, humansTotal );
        assertThat( stats, notNullValue());
        assertThat( stats.getMutantsTotal(), is(mutantsTotal));
        assertThat( stats.getHumansTotal(), is(humansTotal));
        assertThat( stats.getRatio(), is(ratioExpected));
    }

}
