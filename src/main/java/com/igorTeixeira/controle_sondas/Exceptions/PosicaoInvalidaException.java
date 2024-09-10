package com.igorTeixeira.controle_sondas.Exceptions;

public class PosicaoInvalidaException extends RuntimeException {
    public PosicaoInvalidaException(String message) {
        super(message);
    }
}