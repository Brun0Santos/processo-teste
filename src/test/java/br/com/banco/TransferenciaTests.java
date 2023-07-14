package br.com.banco;

import br.com.banco.exceptions.OperadorNotFoundException;
import br.com.banco.exceptions.ParserDataException;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Testes da classe TransferenciaService")
public class TransferenciaTests {
    @Mock
    private TransferenciaRepository repository;

    @InjectMocks
    TransferenciaService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void deveraLancarExceptionPorDadosTransferenciaVaziaTest() {
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());
        OperadorNotFoundException operadorNotFoundException = assertThrows(OperadorNotFoundException.class,
                () -> service.getAllDadosTransferencias());
        assertEquals("Lista de transferencia do operador vazia", operadorNotFoundException.getMessage());
    }

    @Test
    public void deveraLancarExceptionPorFormatoDeDataErradaTest() {
        ParserDataException parserDataException = assertThrows(ParserDataException.class, () ->
                service.getTransferenciaEntreDatas("2022-01-01 12:01:11", "2023-01-01 12:01:aa"));
        assertEquals("Erro ao fazer o parser da data", parserDataException.getMessage());
        verify(repository, Mockito.times(0)).findByTransferenciaEntreDatas(
                LocalDate.of(2022, 5, 13), LocalDate.of(2023, 5, 13));
    }

    @Test
    public void deveraLancarExceptionPorOperadorNaoEncontradoTest() {
        when(repository.findNomeOperadorTransacao("Lucas")).thenReturn(Collections.emptyList());
        OperadorNotFoundException operadorNotFoundException = assertThrows(OperadorNotFoundException.class, () ->
                service.getDadosFromNomeOperador("Lucas"));
        verify(repository, Mockito.times(1)).findNomeOperadorTransacao("Lucas");
        assertEquals("Operador Nao Encontrado", operadorNotFoundException.getMessage());
    }
}