package com.igorTeixeira.controle_sondas.services;

import com.igorTeixeira.controle_sondas.models.Planeta;


public interface PlanetaService {


    Planeta buscarPlanetaPorNome(String nome);

    Iterable<Planeta> buscarTodosPlanetas();
}