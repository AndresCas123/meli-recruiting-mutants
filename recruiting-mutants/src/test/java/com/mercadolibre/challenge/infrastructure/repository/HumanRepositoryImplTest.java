package com.mercadolibre.challenge.infrastructure.repository;

import com.mercadolibre.challenge.core.model.Human;
import com.mercadolibre.challenge.infrastructure.repository.entity.HumanEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class HumanRepositoryImplTest {

    @Mock private HumanDatabase humanDatabase;

    private HumanRepositoryImpl humanRepository;

    @Before
    public void init(){
        humanRepository = new HumanRepositoryImpl( humanDatabase );
    }

    @Test
    public void shouldSaveADN(){
        final HumanEntity humanEntity = new HumanEntity();
        final Human human = new Human(null, false);

        Mockito.when( humanDatabase.save( any(HumanEntity.class) ) ).thenReturn( humanEntity );

        humanRepository.saveADN( human );

        Mockito.verify( humanDatabase ).save( any(HumanEntity.class) );
    }

    @Test
    public void shouldGetHumans(){
        final String nucleotides = "[[\"A\",\"T\",\"G\",\"C\",\"G\",\"A\"],[\"C\",\"A\",\"G\",\"T\",\"G\",\"C\"],[\"T\",\"T\",\"A\",\"T\",\"G\",\"T\"],[\"A\",\"G\",\"A\",\"A\",\"G\",\"G\"],[\"C\",\"C\",\"C\",\"C\",\"T\",\"A\"],[\"T\",\"C\",\"A\",\"C\",\"T\",\"G\"]]";
        final HumanEntity human = new HumanEntity(nucleotides, false);
        final HumanEntity mutant = new HumanEntity(nucleotides, true);
        final List<HumanEntity> humans = Arrays.asList( human, mutant );

        Mockito.when( humanDatabase.findAll() ).thenReturn( humans );

        List<Human> humansResponse = humanRepository.searchHumansADN();
        assertThat( humansResponse, notNullValue());
        assertThat( humansResponse, hasSize( 2 ));
    }

}
