package com.igorTeixeira.controle_sondas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer largura = 5;
    private Integer altura = 5;

    public Planeta(Long id, String nome, Integer largura, Integer altura) {
        this.id = id;
        this.nome = nome;
        this.largura = largura;
        this.altura = altura;
    }

    public Planeta() {
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public boolean isDentroDosLimites(int x, int y) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }


}
