package com.mercadolibre.challenge.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HUMAN")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HumanEntity {

    @Id
    @Column(name = "DNA")
    private String dna;

    @Column(name = "IS_MUTANT")
    private boolean mutant;

}
