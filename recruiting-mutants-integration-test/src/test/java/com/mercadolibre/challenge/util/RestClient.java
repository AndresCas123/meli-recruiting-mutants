package com.mercadolibre.challenge.util;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class RestClient {

    private static final String SERVICE_MUTANT = "/mutant";
    private static final String SERVICE_STATS = "/stats";
    private String url;

    public RestClient() {
        url = "http://localhost:9090";
    }

    public HttpResponse sendToMutant( String body ){
        return Unirest.post(url + SERVICE_MUTANT )
                .header("Content-Type", "application/json")
                .body( body )
                .asString();
    }

    public HttpResponse sendToStats(){
        return Unirest.get(url + SERVICE_STATS )
                .asString();
    }

}
