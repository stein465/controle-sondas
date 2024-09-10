package com.igorTeixeira.controle_sondas.services;

import com.igorTeixeira.controle_sondas.Exceptions.DirecaoInvalidaException;
import com.igorTeixeira.controle_sondas.Exceptions.PosicaoInvalidaException;
import com.igorTeixeira.controle_sondas.dtos.SondaDTO;
import com.igorTeixeira.controle_sondas.models.Sonda;
import com.igorTeixeira.controle_sondas.models.Planeta;
import com.igorTeixeira.controle_sondas.models.enums.Direcao;
import com.igorTeixeira.controle_sondas.repositories.SondaRepository;
import com.igorTeixeira.controle_sondas.services.implementation.PlanetaServiceImp;
import com.igorTeixeira.controle_sondas.services.implementation.SondaServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SondaServiceImpTest {

    @Mock
    private SondaRepository sondaRepository;

    @Mock
    private PlanetaServiceImp planetaServiceImp;

    @InjectMocks
    private SondaServiceImp sondaServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarSondaSucesso() {
        SondaDTO sondaDTO = new SondaDTO(1,1,"NORTE","MARTE");

        Planeta planeta = new Planeta(1L,"Marte",5,5);

        when(planetaServiceImp.buscarPlanetaPorNome("MARTE")).thenReturn(planeta);
        when(sondaRepository.save(any(Sonda.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Sonda sonda = sondaServiceImp.criarSonda(sondaDTO);

        assertNotNull(sonda);
        assertEquals(1, sonda.getPosicaoX());
        assertEquals(1, sonda.getPosicaoY());
        assertEquals(Direcao.NORTE, sonda.getDirecao());
        assertEquals(planeta, sonda.getPlaneta());
        verify(sondaRepository).save(any(Sonda.class));
    }


    @Test
    public void testCriarSondaPosicaoInvalidaJaComSonda() {
        SondaDTO sondaDTO = new SondaDTO(0, 0, "NORTE", "MARTE");
        Planeta planeta = new Planeta(1L, "Marte", 5, 5);
        Sonda sondaMock = new Sonda(2L, 0, 0, Direcao.NORTE, planeta);

        PlanetaServiceImp planetaServiceImpMock = mock(PlanetaServiceImp.class);
        SondaRepository sondaRepositoryMock = mock(SondaRepository.class);

        when(planetaServiceImpMock.buscarPlanetaPorNome("MARTE")).thenReturn(planeta);
        when(sondaRepositoryMock.findByPosicaoXAndPosicaoY(0, 0)).thenReturn(Optional.of(sondaMock));

        SondaServiceImp sondaServiceImp = new SondaServiceImp(sondaRepositoryMock, planetaServiceImpMock);

        PosicaoInvalidaException thrown = assertThrows(PosicaoInvalidaException.class, () -> {
            sondaServiceImp.criarSonda(sondaDTO);
        });

        assertEquals("Já existe outra sonda na posição X: 0, Y: 0", thrown.getMessage());
    }

    @Test
    public void testCriarSondaPosicaoInvalidaForaDoPlaneta() {
        SondaDTO sondaDTO = new SondaDTO(5, 5, "NORTE", "MARTE");
        Planeta planeta = new Planeta(1L, "Marte", 5, 5);

        when(planetaServiceImp.buscarPlanetaPorNome("MARTE")).thenReturn(planeta);


        PosicaoInvalidaException thrown = assertThrows(PosicaoInvalidaException.class, () -> {
            sondaServiceImp.criarSonda(sondaDTO);
        });

        assertEquals("Posição fora dos limites do planeta. Posição X: 5, Y: 5", thrown.getMessage());
    }

    @Test
    public void testCriarSondaDirecaoInvalida() {
        SondaDTO sondaDTO = new SondaDTO(5, 5, "plutao", "MARTE");

        DirecaoInvalidaException thrown = assertThrows(DirecaoInvalidaException.class, () -> {
            sondaServiceImp.criarSonda(sondaDTO);
        });

        assertEquals("Direção não é válida: plutao", thrown.getMessage());
    }


    @Test
    public void testMoverSondaSucesso() {
        Planeta planeta = new Planeta(1L,"Marte",5,5);
        Sonda sonda = new Sonda(1L, 1, 1, Direcao.NORTE, planeta);
        when(sondaRepository.findById(1L)).thenReturn(Optional.of(sonda));
        when(sondaRepository.save(any(Sonda.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(planetaServiceImp.buscarPlanetaPorNome(anyString())).thenReturn(planeta);

        Sonda sondaMovida = sondaServiceImp.moverSonda(1L, "M");


        assertEquals(1, sondaMovida.getPosicaoX());
        assertEquals(2, sondaMovida.getPosicaoY());
        verify(sondaRepository).save(any(Sonda.class));
    }

    @Test
    public void testMoverSondaNaoEncontrada() {
        Sonda sonda = new Sonda(-2L, 1, 1, Direcao.NORTE, new Planeta());
        when(sondaRepository.findById(sonda.getId())).thenThrow(new NoSuchElementException("Sonda não encontrada para o ID: -2"));

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            sondaServiceImp.moverSonda(-2L, "");
        });

        assertEquals("Sonda não encontrada para o ID: -2", thrown.getMessage());
    }

    @Test
    public void testMoverSondaComandoInvalido() {
        Sonda sonda = new Sonda(1L, 1, 1, Direcao.NORTE, new Planeta());
        when(sondaRepository.findById(1L)).thenReturn(Optional.of(sonda));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sondaServiceImp.moverSonda(1L, "X");
        });

        assertEquals("Comando inválido: X", thrown.getMessage());
    }

    @Test
    public void testMoverSondaPosicaoInvalidaForaDoPlaneta() {
        Planeta planeta = new Planeta(1L, "Marte", 5, 5);
        Sonda sonda = new Sonda(1L, 5, 5, Direcao.NORTE, planeta);
        when(sondaRepository.findById(1L)).thenReturn(Optional.of(sonda));
        when(planetaServiceImp.buscarPlanetaPorNome(anyString())).thenReturn(planeta);

        PosicaoInvalidaException thrown = assertThrows(PosicaoInvalidaException.class, () -> {
            sondaServiceImp.moverSonda(1L, "M"); // Movimento para fora dos limites
        });

        assertEquals("Posição fora dos limites do planeta. Posição X: 5, Y: 6", thrown.getMessage());
    }

    @Test
    public void testMoverSondaPosicaoJaOcupada() {
        Planeta planeta = new Planeta(1L, "Marte", 5, 5);
        Sonda sonda = new Sonda(1L, 1, 1, Direcao.NORTE, planeta);
        Sonda sondaOcupandoPosicao = new Sonda(2L, 1, 2, Direcao.SUL, planeta);

        PlanetaServiceImp planetaServiceImpMock = mock(PlanetaServiceImp.class);
        SondaRepository sondaRepositoryMock = mock(SondaRepository.class);

        when(sondaRepositoryMock.findById(1L)).thenReturn(Optional.of(sonda));
        when(planetaServiceImpMock.buscarPlanetaPorNome("MARTE")).thenReturn(planeta);
        when(sondaRepositoryMock.findByPosicaoXAndPosicaoY(1, 2)).thenReturn(Optional.of(sondaOcupandoPosicao));

        SondaServiceImp sondaServiceImp = new SondaServiceImp(sondaRepositoryMock, planetaServiceImpMock);

        PosicaoInvalidaException thrown = assertThrows(PosicaoInvalidaException.class, () -> {
            sondaServiceImp.moverSonda(1L, "M"); // Movimento para posição ocupada
        });

        assertEquals("Já existe outra sonda na posição X: 1, Y: 2", thrown.getMessage());
    }
}
