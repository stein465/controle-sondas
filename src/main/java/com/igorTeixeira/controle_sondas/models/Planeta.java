package com.igorTeixeira.controle_sondas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer largura;
    private Integer altura;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planeta planeta = (Planeta) o;
        return Objects.equals(id, planeta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
