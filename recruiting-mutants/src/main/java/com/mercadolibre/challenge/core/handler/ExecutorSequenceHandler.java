package com.mercadolibre.challenge.core.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorSequenceHandler {

    private ExecutorSequenceHandler() {
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool( 25 );

    public static ExecutorService getExecutorService(){
        return executorService;
    }


}
