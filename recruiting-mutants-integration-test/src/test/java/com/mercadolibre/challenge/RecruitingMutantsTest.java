package com.mercadolibre.challenge;

import com.mercadolibre.challenge.util.RestClient;
import kong.unirest.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecruitingMutantsTest {

    private static final String SERVICE_NAME = "recruiting-mutants_1";

    private DockerComposeContainer container;
    private RestClient restClient;

    @Before
    public void init(){
        configureApp();
        restClient = new RestClient();
    }

    @After
    public void destroy(){
        container.stop();
    }

    @Test
    public void shouldProcessMutant(){
        final String bodyMutant = "{\n\t\"dna\": [\n\t\t\"ATGCGA\",\n\t\t\"CAGTGC\",\n\t\t\"TTATGT\",\n\t\t\"AGAAGG\",\n\t\t\"CCCCTA\",\n\t\t\"TCACTG\"\n\t]\n}";
        HttpResponse response = restClient.sendToMutant( bodyMutant );
        assertThat( response, notNullValue());
        assertThat( response.getStatus(), is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnForbiddenWhenDNAIsInvalid(){
        final String bodyInvalid = "{\n\t\"dna\": [\n\t\t\"XTGCGA\",\n\t\t\"CAGTGC\",\n\t\t\"TTATGT\",\n\t\t\"AGAAGG\",\n\t\t\"CCCCTA\",\n\t\t\"TCACTG\"\n\t]\n}";
        HttpResponse response = restClient.sendToMutant( bodyInvalid );
        assertThat( response, notNullValue());
        assertThat( response.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void shouldReturnForbiddenWhenDNAIsNotAMatrix(){
        final String invalidMatrix = "{\n\t\"dna\": [\n\t\t\"ATCGGA\",\n\t\t\"CAGTGC\"\n\t]\n}";
        HttpResponse response = restClient.sendToMutant( invalidMatrix );
        assertThat( response, notNullValue());
        assertThat( response.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void shouldReturnForbiddenWhenDNAIsHuman(){
        final String bodyHuman = "{\n\t\"dna\": [\n\t\t\"ATCGAT\",\n\t\t\"CGATCG\",\n\t\t\"ATCGAT\",\n\t\t\"CGATCG\",\n\t\t\"ATCGAT\",\n\t\t\"CGATCG\"\n\t]\n}";
        HttpResponse response = restClient.sendToMutant( bodyHuman );
        assertThat( response, notNullValue());
        assertThat( response.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void shouldReturnBadRequestWhenDNAIsNull(){
        final String body = "{\n\t\"dna\": null\n}";
        HttpResponse response = restClient.sendToMutant( body );
        assertThat( response, notNullValue());
        assertThat( response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void shouldReturnStatsWithOneHumanMutantAndRatio(){
        final String statsExpected = "{\"ratio\":1.0,\"count_mutant_dna\":1.0,\"count_human_dna\":1.0}";
        final String bodyMutant = "{\n\t\"dna\": [\n\t\t\"ATGCGA\",\n\t\t\"CAGTGC\",\n\t\t\"TTATGT\",\n\t\t\"AGAAGG\",\n\t\t\"CCCCTA\",\n\t\t\"TCACTG\"\n\t]\n}";
        final String bodyHuman = "{\n\t\"dna\": [\n\t\t\"ATCGAT\",\n\t\t\"CGATCG\",\n\t\t\"ATCGAT\",\n\t\t\"CGATCG\",\n\t\t\"ATCGAT\",\n\t\t\"CGATCG\"\n\t]\n}";
        restClient.sendToMutant( bodyMutant );
        restClient.sendToMutant( bodyHuman );

        HttpResponse response = restClient.sendToStats();
        assertThat( response, notNullValue());
        assertThat( response.getBody(), is( statsExpected ) );
        assertThat( response.getStatus(), is(HttpStatus.OK.value()));
    }

    private void configureApp(){
        final int times = 1;
        final String messageWait = ".*Tomcat started on port.*";
        final String dockerComposeFile = "src/test/resources/docker-compose.yml";

        File file = new File(dockerComposeFile);
        container = new DockerComposeContainer( file )
                .waitingFor( SERVICE_NAME, Wait.forLogMessage( messageWait, times ) )
                .withLocalCompose(true);
        container.start();
    }

}
