package com.banco.api_pagamentos.domain.pagamento;

import com.banco.api_pagamentos.domain.descricao.DescricaoService;
import com.banco.api_pagamentos.domain.forma_pagamento.FormaPagamentoService;
import com.banco.api_pagamentos.domain.pagamento.validacoes.forma_pagamento.ValidadorFormaPagamento;
import com.banco.api_pagamentos.domain.transacao.Transacao;
import com.banco.api_pagamentos.domain.transacao.TransacaoService;
import com.banco.api_pagamentos.domain.transacao_pagamento.TransacaoPagamentoService;
import com.banco.api_pagamentos.exceptions.HttpNotFoundException;
import com.banco.api_pagamentos.exceptions.ValidationException;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.banco.api_pagamentos.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService unit;

    @Mock
    private TransacaoService transacaoService;

    @Mock
    private TransacaoPagamentoService transacaoPagamentoService;

    @Mock
    private FormaPagamentoService formaPagamentoService;

    @Mock
    private DescricaoService descricaoService;

    @Mock
    private List<ValidadorFormaPagamento> validadoresFormaPagamento;

    @Mock
    private Page<Transacao> transacaoPage;

    @Mock
    private Page<Object> responsePage;

    @Test
    void realizarPagamento_NaoExisteTransacaoIdTest() {

        when(transacaoService.realizarPagamento(any())).thenReturn(TRANSACAO_AUTORIZADA);
        when(transacaoService.existsById(anyLong())).thenReturn(Boolean.FALSE);
        when(formaPagamentoService.obterPorTipo(any())).thenReturn(FORMA_PAGAMENTO);

        assertDoesNotThrow(() -> unit.realizarPagamento(PAGAMENTO_REQUEST));
        verify(transacaoPagamentoService).save(any());
        verify(validadoresFormaPagamento).forEach(any());
    }

    @Test
    void realizarPagamento_ExisteTransacaoIdTest() {

        when(transacaoService.existsById(anyLong())).thenReturn(Boolean.TRUE);

        assertThrows(
                EntityExistsException.class,
                () -> unit.realizarPagamento(PAGAMENTO_REQUEST));

        verify(transacaoService).existsById(any());
        verify(formaPagamentoService, never()).obterPorTipo(any());
        verify(transacaoPagamentoService, never()).save(any());
    }

    @Test
    void realizarPagamento_ErroFormatoDataTest() {

        when(transacaoService.existsById(anyLong())).thenReturn(Boolean.FALSE);

        assertThrows(
                ValidationException.class,
                () -> unit.realizarPagamento(PAGAMENTO_REQUEST_ERRO_DATA));

        verify(transacaoPagamentoService, never()).save(any());
    }

    @Test
    void realizarEstorno_Sucesso() {

        when(transacaoService.consultarPorId(anyLong())).thenReturn(TRANSACAO);

        assertDoesNotThrow(() -> unit.realizarEstorno(1L));
        verify(descricaoService).save(any());
    }

    @Test
    void realizarEstorno_Erro() {

        when(transacaoService.consultarPorId(anyLong())).thenReturn(TRANSACAO_NEGADO);

        assertThrows(
                ValidationException.class,
                () -> unit.realizarEstorno(1L));

        verify(descricaoService, never()).save(any());
    }

    @Test
    void consultarTransacaoPorId() {

        when(transacaoService.consultarPorId(anyLong())).thenReturn(TRANSACAO);

        assertDoesNotThrow(() -> unit.consultarTransacaoPorId(1L));
        verify(transacaoService).consultarPorId(anyLong());
    }

    @Test
    void consultarTodasTransacoes_Success() {

        when(transacaoService.consultarTodas()).thenReturn(List.of(TRANSACAO));


        assertDoesNotThrow(() -> unit.consultarTodasTransacoes());
        verify(transacaoService).consultarTodas();
    }

    @Test
    void consultarTodasTransacoes_Erro() {

        when(transacaoService.consultarTodas()).thenReturn(List.of());

        assertThrows(
                HttpNotFoundException.class,
                () -> unit.consultarTodasTransacoes());

        verify(transacaoService).consultarTodas();
    }

    @Test
    void consultarTodasTransacoesPaginacao_Success() {

        when(transacaoService.consultarTodas(any())).thenReturn(transacaoPage);
        when(transacaoPage.map(any())).thenReturn(responsePage);

        var pageable = PageRequest.of(0, 1);

        assertDoesNotThrow(() -> unit.consultarTodasTransacoesPaginacao(pageable));

        verify(transacaoService).consultarTodas(any());
        verify(transacaoPage).map(any());
        verify(responsePage).isEmpty();
    }

    @Test
    void consultarTodasTransacoesPaginacao_Erro() {

        when(transacaoService.consultarTodas(any())).thenReturn(transacaoPage);
        when(transacaoPage.map(any())).thenReturn(responsePage);
        when(responsePage.isEmpty()).thenReturn(true);

        var pageable = PageRequest.of(0, 1);

        assertThrows(
                HttpNotFoundException.class,
                () -> unit.consultarTodasTransacoesPaginacao(pageable));

        verify(transacaoService).consultarTodas(any());
        verify(transacaoPage).map(any());
        verify(responsePage).isEmpty();
    }
}