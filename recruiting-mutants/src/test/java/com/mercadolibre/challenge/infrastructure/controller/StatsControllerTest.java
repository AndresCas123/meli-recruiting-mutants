package com.mercadolibre.challenge.infrastructure.controller;

import com.mercadolibre.challenge.core.model.Stats;
import com.mercadolibre.challenge.core.service.StatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTest {

    @Mock
    private StatsService statsService;
    private StatsController statsController;

    @Before
    public void init(){
        statsController = new StatsController( statsService );
    }

    @Test
    public void shouldReturnStats(){
        Stats stats = new Stats(0,0);
        Mockito.when( statsService.createStats() ).thenReturn( stats );

        Stats statsResponse = statsController.getStats();
        assertThat( statsResponse, is( stats ));
    }

}
