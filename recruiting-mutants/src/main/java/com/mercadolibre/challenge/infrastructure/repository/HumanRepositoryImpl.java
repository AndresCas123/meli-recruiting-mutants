package com.mercadolibre.challenge.infrastructure.repository;

import com.mercadolibre.challenge.core.model.Human;
import com.mercadolibre.challenge.core.model.Nucleotide;
import com.mercadolibre.challenge.core.repository.HumanRepository;
import com.mercadolibre.challenge.infrastructure.repository.entity.HumanEntity;
import com.mercadolibre.challenge.core.util.JsonUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class HumanRepositoryImpl implements HumanRepository {

    private final HumanDatabase humanDatabase;

    public HumanRepositoryImpl(HumanDatabase humanDatabase) {
        this.humanDatabase = humanDatabase;
    }

    @Override
    public void saveADN(Human human) {
        HumanEntity humanEntity = convertTo( human );
        humanDatabase.save( humanEntity );
    }

    @Override
    public List<Human> searchHumansADN() {
        Iterable<HumanEntity> humanEntities = humanDatabase.findAll();
        return StreamSupport.stream( humanEntities.spliterator(), true )
                .map( humanEntity -> convertTo( humanEntity ) )
                .collect(Collectors.toList());
    }

    private Human convertTo( HumanEntity humanEntity ){
        Nucleotide[][] nucleotides = JsonUtils.toNucleotides( humanEntity.getDna() );
        return new Human( nucleotides, humanEntity.isMutant() );
    }

    private HumanEntity convertTo( Human human ){
        final String dna = JsonUtils.toString( human.getNucleotides() );
        return new HumanEntity( dna, human.isMutant() );
    }
}
