package com.igorTeixeira.controle_sondas.services.implementation;

import com.igorTeixeira.controle_sondas.Exceptions.DirecaoInvalidaException;
import com.igorTeixeira.controle_sondas.Exceptions.PosicaoInvalidaException;
import com.igorTeixeira.controle_sondas.dtos.SondaDTO;
import com.igorTeixeira.controle_sondas.models.Sonda;
import com.igorTeixeira.controle_sondas.models.enums.Direcao;
import com.igorTeixeira.controle_sondas.repositories.SondaRepository;
import com.igorTeixeira.controle_sondas.models.Planeta;
import com.igorTeixeira.controle_sondas.services.PlanetaService;
import com.igorTeixeira.controle_sondas.services.SondaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class SondaServiceImp implements SondaService {

    private final SondaRepository sondaRepository;
    private final PlanetaService planetaService;

    @Autowired
    public SondaServiceImp(SondaRepository sondaRepository, PlanetaServiceImp planetaService) {
        this.sondaRepository = sondaRepository;
        this.planetaService = planetaService;
    }

    public Iterable<Sonda> buscarTodasSondas() {
        return sondaRepository.findAll();
    }

    public Sonda buscarSondaPorId(long id) {
        return sondaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sonda não encontrada para o ID: " + id));
    }

    public Sonda criarSonda(SondaDTO novaSondaDTO) {
        Planeta planeta = planetaService.buscarPlanetaPorNome(novaSondaDTO.getPlaneta());
        Direcao direcao = validarDirecao(novaSondaDTO.getDirecao());

        validarPosicao(novaSondaDTO.getPosicaoX(), novaSondaDTO.getPosicaoY(), null, planeta);

        Sonda sonda = new Sonda(null, novaSondaDTO.getPosicaoX(), novaSondaDTO.getPosicaoY(), direcao, planeta);
        return sondaRepository.save(sonda);
    }

    public void deletarSonda(Long sondaId) {
        sondaRepository.deleteById(sondaId);
    }

    public Sonda moverSonda(Long sondaId, String comandos) {
        Sonda sonda = buscarSondaPorId(sondaId);
        Planeta planeta = sonda.getPlaneta();

        for (char comando : comandos.toCharArray()) {
            executarComando(comando, sonda, planeta);
        }

        return sondaRepository.save(sonda);
    }

    private void executarComando(char comando, Sonda sonda, Planeta planeta) {
        switch (comando) {
            case 'M':
                moverParaFrente(sonda, planeta);
                break;
            case 'L':
                sonda.setDirecao(sonda.getDirecao().virarEsquerda());
                break;
            case 'R':
                sonda.setDirecao(sonda.getDirecao().virarDireita());
                break;
            default:
                throw new IllegalArgumentException("Comando inválido: " + comando);
        }
    }

    private void moverParaFrente(Sonda sonda, Planeta planeta) {
        int x = sonda.getPosicaoX();
        int y = sonda.getPosicaoY();
        Direcao direcao = sonda.getDirecao();

        switch (direcao) {
            case NORTE -> y++;
            case LESTE -> x++;
            case SUL -> y--;
            case OESTE -> x--;
        }

        validarPosicao(x, y, sonda.getId(), planeta);

        sonda.setPosicaoX(x);
        sonda.setPosicaoY(y);
    }

    private void validarPosicao(int x, int y, Long sondaId, Planeta planeta) {
        if (!planeta.isDentroDosLimites(x, y)) {
            throw new PosicaoInvalidaException("Posição fora dos limites do planeta. Posição X: " + x + ", Y: " + y);
        }

        Optional<Sonda> sondaExistente = sondaRepository.findByPosicaoXAndPosicaoY(x, y);
        if (sondaExistente.isPresent() ) {
            throw new PosicaoInvalidaException("Já existe outra sonda na posição X: " + x + ", Y: " + y);
        }
    }

    private Direcao validarDirecao(String direcao) {
        try {
            return Direcao.valueOf(direcao.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DirecaoInvalidaException("Direção não é válida: " + direcao);
        }
    }
}

