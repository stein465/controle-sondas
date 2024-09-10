package com.igorTeixeira.controle_sondas.models.enums;

public enum Direcao {
    NORTE, LESTE, SUL, OESTE;

    public Direcao virarEsquerda() {
        switch (this) {
            case NORTE: return OESTE;
            case OESTE: return SUL;
            case SUL: return LESTE;
            case LESTE: return NORTE;
            default: throw new IllegalStateException("Direção desconhecida: " + this);
        }
    }

    public Direcao virarDireita() {
        switch (this) {
            case NORTE: return LESTE;
            case LESTE: return SUL;
            case SUL: return OESTE;
            case OESTE: return NORTE;
            default: throw new IllegalStateException("Direção desconhecida: " + this);
        }
    }
}
