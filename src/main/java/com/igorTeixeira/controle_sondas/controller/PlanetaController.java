package com.igorTeixeira.controle_sondas.controller;


import com.igorTeixeira.controle_sondas.models.Planeta;
import com.igorTeixeira.controle_sondas.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("planeta")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;


    @GetMapping(path = "/")
    public ResponseEntity<Iterable<Planeta>> buscarTodos() {
        try {
            Iterable<Planeta> planetas = planetaService.buscarTodosPlanetas();
            return ResponseEntity.ok(planetas);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
