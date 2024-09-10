package com.igorTeixeira.controle_sondas.controller;


import com.igorTeixeira.controle_sondas.dtos.ComandoDTO;
import com.igorTeixeira.controle_sondas.dtos.SondaDTO;
import com.igorTeixeira.controle_sondas.models.Sonda;
import com.igorTeixeira.controle_sondas.services.SondaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeErrorException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("sonda")
public class SondaController {

    @Autowired
    private SondaService SondaService;


    @GetMapping(path = "/")
    public ResponseEntity<Iterable<Sonda>> buscarTodos(){
        Iterable<Sonda> response = SondaService.buscarTodasSondas();

        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> buscarPorId(@PathVariable long id){
        try{
            Sonda response = SondaService.buscarSondaPorId(id);
            return ResponseEntity.ok().body("" + response);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> criarSonda(@RequestBody SondaDTO novaSonda){
        try{
            Sonda response = SondaService.criarSonda(novaSonda);
            return ResponseEntity
                    .ok()
                    .body("Sonda " + response.getId() + " criada no planeta " + response.getPlaneta().getNome() +  " na posicao  (" + response.getPosicaoX() + "," + response.getPosicaoY() + ")");
        }catch (RuntimeErrorException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/mover/{id}")
    public ResponseEntity<String> moverSonda(@PathVariable  Long id, @RequestBody ComandoDTO comando ){
        try{
            Sonda response = SondaService.moverSonda(id, comando.getComando());
            return ResponseEntity
                    .ok()
                    .body("Sonda " + response.getId() + " se encontra na posicao " + response.getPosicaoX() + "," + response.getPosicaoY() + "na direcao: " + response.getDirecao());
        }catch (RuntimeErrorException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSonda(@PathVariable Long id) {
        try {
            SondaService.deletarSonda(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sonda deletada com sucesso");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
