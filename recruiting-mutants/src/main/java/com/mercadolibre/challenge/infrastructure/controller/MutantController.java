package com.mercadolibre.challenge.infrastructure.controller;

import com.mercadolibre.challenge.core.exception.DNAException;
import com.mercadolibre.challenge.core.service.MutantService;
import com.mercadolibre.challenge.infrastructure.controller.model.MutantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MutantController {

    private final MutantService mutantService;

    @Autowired
    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("mutant")
    public boolean storeHuman(@RequestBody @Valid MutantRequest mutantRequest) throws DNAException {
        String[][] sequences = mutantRequest.getDna().stream().map( sequence-> sequence.split("") ).toArray( String[][]::new );
        return mutantService.isMutant( sequences );
    }

}
