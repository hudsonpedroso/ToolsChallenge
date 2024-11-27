package com.banco.api_pagamentos.domain.transacao_pagamento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.banco.api_pagamentos.TestConstants.TRANSACAO_PAGAMENTO;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransacaoPagamentoServiceTest {

    @InjectMocks
    private TransacaoPagamentoService unit;

    @Mock
    private TransacaoPagamentoRepository transacaoPagamentoRepository;

    @Test
    void save() {
        assertDoesNotThrow(() -> unit.save(TRANSACAO_PAGAMENTO));
        verify(transacaoPagamentoRepository).save(any());
    }
}