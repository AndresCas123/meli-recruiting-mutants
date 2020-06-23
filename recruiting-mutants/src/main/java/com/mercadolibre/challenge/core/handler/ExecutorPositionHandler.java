package com.mercadolibre.challenge.core.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPositionHandler {

    private ExecutorPositionHandler() {
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool( 50 );

    public static ExecutorService getExecutorService(){
        return executorService;
    }


}
