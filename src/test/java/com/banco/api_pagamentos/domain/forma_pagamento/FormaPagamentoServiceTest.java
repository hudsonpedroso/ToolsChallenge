package com.banco.api_pagamentos.domain.forma_pagamento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FormaPagamentoServiceTest {

    @InjectMocks
    private FormaPagamentoService unit;

    @Mock
    private FormaPagamentoRepository formaPagamentoRepository;

    @Test
    void obterPorTipo() {
        assertDoesNotThrow(() -> unit.obterPorTipo("AVISTA"));
        verify(formaPagamentoRepository).findByTipo(any());
    }
}