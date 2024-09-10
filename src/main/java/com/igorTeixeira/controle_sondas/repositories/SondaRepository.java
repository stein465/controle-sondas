package com.igorTeixeira.controle_sondas.repositories;

import com.igorTeixeira.controle_sondas.models.Sonda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SondaRepository extends CrudRepository<Sonda, Long> {

    Optional<Sonda> findByPosicaoXAndPosicaoY(int posicaoX, int posicaoY);
}
