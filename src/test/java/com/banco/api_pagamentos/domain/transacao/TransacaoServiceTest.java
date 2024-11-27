package com.banco.api_pagamentos.domain.transacao;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static com.banco.api_pagamentos.TestConstants.TRANSACAO;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService unit;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Test
    void realizarPagamento() {

        assertDoesNotThrow(() -> unit.realizarPagamento(TRANSACAO));
        verify(transacaoRepository).save(any());
    }

    @Test
    void consultarPorId_Success() {

        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.of(TRANSACAO));

        assertDoesNotThrow(() -> unit.consultarPorId(1L));
        verify(transacaoRepository).findById(anyLong());
    }

    @Test
    void consultarPorId_Erro() {

        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> unit.consultarPorId(1L));

        verify(transacaoRepository).findById(anyLong());
    }

    @Test
    void existsById() {

        assertDoesNotThrow(() -> unit.existsById(1L));
        verify(transacaoRepository).existsById(anyLong());
    }

    @Test
    void consultarTodas() {

        assertDoesNotThrow(() -> unit.consultarTodas());
        verify(transacaoRepository).findAll();
    }

    @Test
    void consultarTodasComPaginacao() {

        var pageable = PageRequest.of(0, 1);

        assertDoesNotThrow(() -> unit.consultarTodas(pageable));
        verify(transacaoRepository).findAll(pageable);
    }
}