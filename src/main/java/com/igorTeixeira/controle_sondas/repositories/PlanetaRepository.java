package com.igorTeixeira.controle_sondas.repositories;

import com.igorTeixeira.controle_sondas.models.Planeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetaRepository extends CrudRepository<Planeta, Long> {

    Optional<Planeta> findByNome(String nome);
}
