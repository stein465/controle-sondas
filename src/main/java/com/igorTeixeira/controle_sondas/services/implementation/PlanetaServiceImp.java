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

    public Planeta buscarPlanetaPorId(long id) {
        return planetaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Planeta não encontrado com id: " + id));
    }

    public Planeta buscarPlanetaPorNome(String nome) {

        return planetaRepository.findByNome(nome)
                .orElseThrow(() -> new NoSuchElementException("Planeta não encontrado com nome: " + nome));
    }

    //TOdo
    //talvez nem precise desse controller nem do respectivo service
    //nao será preciso resgatar o planeta



}
