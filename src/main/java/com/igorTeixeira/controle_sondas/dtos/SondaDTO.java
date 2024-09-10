package com.igorTeixeira.controle_sondas.dtos;

import com.igorTeixeira.controle_sondas.models.Planeta;

public class SondaDTO {
    private int posicaoX;
    private int posicaoY;
    private String direcao;
    private String nomePlaneta;

    public String getPlaneta() {
        return nomePlaneta;
    }

    public SondaDTO() {}

    public SondaDTO(int posicaoX, int posicaoY, String direcao, String nomePlaneta) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.nomePlaneta = nomePlaneta;
    }

    public String getNomePlaneta() {
        return nomePlaneta;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public String getDirecao() {
        return direcao;
    }
}
