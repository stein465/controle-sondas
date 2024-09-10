package com.igorTeixeira.controle_sondas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import com.igorTeixeira.controle_sondas.models.enums.Direcao;

@Entity
public class Sonda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int posicaoX;
    private int posicaoY;
    private Direcao direcao;

    @ManyToOne
    @JoinColumn(name = "planeta_id", nullable = false)
    private Planeta planeta;

    public Sonda() {}

    public Sonda(Long id, int posicaoX, int posicaoY, Direcao direcao, Planeta planeta) {
        this.id = id;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.planeta = planeta;
    }


    public Long getId() {
        return id;
    }


    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    @Override
    public String toString() {
        return "Sonda{" +
                "id=" + id +
                ", posicaoX=" + posicaoX +
                ", posicaoY=" + posicaoY +
                ", direcao='" + direcao + '\'' +
                ", planeta=" + planeta +
                '}';
    }
}
