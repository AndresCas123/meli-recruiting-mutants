package com.mercadolibre.challenge.infrastructure.controller;

import com.mercadolibre.challenge.core.exception.DNAException;
import com.mercadolibre.challenge.core.service.MutantService;
import com.mercadolibre.challenge.infrastructure.controller.model.MutantRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {

    @Mock private MutantService mutantService;
    private MutantController mutantController;

    @Before
    public void init(){
        mutantController = new MutantController( mutantService );
    }

    @Test
    public void shouldReturnTrueWhenDNAISValid() throws DNAException {
        List<String> dna = Arrays.asList( "A", "A", "A", "A" );
        MutantRequest request = new MutantRequest( dna );

        Mockito.when( mutantService.isMutant( any(String[][].class) ) ).thenReturn( Boolean.TRUE );

        boolean response = mutantController.storeHuman( request );
        assertThat( response, is( TRUE ));

        Mockito.verify( mutantService ).isMutant( any(String[][].class) );
    }

}
