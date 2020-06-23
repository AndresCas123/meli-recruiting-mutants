package com.mercadolibre.challenge.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.challenge.core.model.Nucleotide;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static String toString(Object object ) {
        try{
            return mapper.writeValueAsString( object );
        } catch ( JsonProcessingException e){
            return null;
        }
    }

    public static Nucleotide[][] toNucleotides(String data ) {
        try{
            return mapper.readValue(data, Nucleotide[][].class);
        } catch ( JsonProcessingException e){
            return null;
        }
    }

}
