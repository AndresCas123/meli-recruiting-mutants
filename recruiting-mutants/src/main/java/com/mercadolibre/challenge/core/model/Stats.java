package com.mercadolibre.challenge.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Stats {

    @JsonProperty("count_mutant_dna")
    private final double mutantsTotal;

    @JsonProperty("count_human_dna")
    private final double humansTotal;
    private final double ratio;

    public Stats(double mutantsTotal, double humansTotal) {
        this.mutantsTotal = mutantsTotal;
        this.humansTotal = humansTotal;
        ratio = humansTotal > 0 ? mutantsTotal / humansTotal : 0;
    }
}
