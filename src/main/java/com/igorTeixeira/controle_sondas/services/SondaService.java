package com.igorTeixeira.controle_sondas.services;

import com.igorTeixeira.controle_sondas.dtos.SondaDTO;
import com.igorTeixeira.controle_sondas.models.Sonda;

public interface SondaService {
    Iterable<Sonda> buscarTodasSondas();
    Sonda buscarSondaPorId(long id);
    Sonda criarSonda(SondaDTO novaSondaDTO);
    void deletarSonda(Long sondaId);
    Sonda moverSonda(Long sondaId, String comandos);
}
