package com.mercadolibre.challenge.core.service;

import com.mercadolibre.challenge.core.model.Human;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.model.Stats;
import com.mercadolibre.challenge.core.repository.HumanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.mercadolibre.challenge.core.model.Nucleotide.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

    @Mock
    private HumanRepository humanRepository;
    private StatsService statsService;

    @Before
    public void init(){
        statsService = new StatsService(humanRepository);
    }

    @Test
    public void createStatsSuccessfully(){
        final Nucleotide[][] nucleotides = {
                {A, A, A, A},
                {A, T, C, G},
                {T, C, G, A},
                {T, C, G, A}
        };
        Human human = new Human( nucleotides, false);
        Human mutant = new Human( nucleotides, true);
        List<Human> humans = Arrays.asList( human, mutant );
        Mockito.when( humanRepository.searchHumansADN() ).thenReturn( humans );

        Stats statsResponse = statsService.createStats();
        assertThat( statsResponse, notNullValue());
        assertThat( statsResponse.getMutantsTotal(), is( 1.0 ));
        assertThat( statsResponse.getHumansTotal(), is( 1.0 ));
        assertThat( statsResponse.getRatio(), is( 1.0 ));

        Mockito.verify( humanRepository ).searchHumansADN();
    }

}
