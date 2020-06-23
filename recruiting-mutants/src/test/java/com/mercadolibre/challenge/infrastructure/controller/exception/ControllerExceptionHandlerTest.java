package com.mercadolibre.challenge.infrastructure.controller.exception;

import com.mercadolibre.challenge.core.exception.DNAException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ControllerExceptionHandlerTest {

    @Mock private MethodArgumentNotValidException methodArgumentNotValidException;
    private ControllerExceptionHandler controllerExceptionHandler;

    @Before
    public void init(){
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    public void shouldReturnResponseEntityWithHttpForbidden(){
        final DNAException exception = new DNAException("Message");
        ResponseEntity<Object> response = controllerExceptionHandler.handleHumanException( exception );
        assertThat( response, notNullValue());
        assertThat( response.getStatusCode(), is(HttpStatus.FORBIDDEN));
        assertThat( response.getBody().toString(), is(exception.getMessage()));
    }

    @Test
    public void shouldReturnResponseEntityWithHttpBadRequest(){
        final String message = "Message";
        Mockito.when( methodArgumentNotValidException.getMessage() ).thenReturn( message );

        ResponseEntity<Object> response = controllerExceptionHandler.handleValidationExceptions( methodArgumentNotValidException );
        assertThat( response, notNullValue());
        assertThat( response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat( response.getBody().toString(), is(message));
    }
}
