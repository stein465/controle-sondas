package com.igorTeixeira.controle_sondas.services.implementation;

import com.igorTeixeira.controle_sondas.repositories.PlanetaRepository;
import com.igorTeixeira.controle_sondas.models.Planeta;
import com.igorTeixeira.controle_sondas.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PlanetaServiceImp implements PlanetaService {

    @Autowired
    PlanetaRepository planetaRepository;


    public Planeta buscarPlanetaPorNome(String nome) {

        return planetaRepository.findByNome(nome)
                .orElseThrow(() -> new NoSuchElementException("Planeta não encontrado com nome: " + nome));
    }

    public Iterable<Planeta> buscarTodosPlanetas() {
        Iterable<Planeta> planetas = planetaRepository.findAll();
        if (!planetas.iterator().hasNext()) {
            throw new NoSuchElementException("Nenhum planeta encontrado.");
        }
        return planetas;
    }



}
