package com.banco.api_pagamentos.controller;

import com.banco.api_pagamentos.domain.pagamento.PagamentoService;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoRequest;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.banco.api_pagamentos.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PagamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PagamentoService pagamentoService;

    @Autowired
    private JacksonTester<PagamentoRequest> pagamentoRequestJson;

    @Autowired
    private JacksonTester<PagamentoResponse> pagamentoResponseJson;


    @Autowired
    private JacksonTester<List<PagamentoResponse>> pagamentoResponseListJson;

    @Test
    @DisplayName("Deve devolver código 400 ao enviar um pagamento com dados inválidos")
    void realizarPagamento_dadosInvalidos() throws Exception {

        var response = mvc.perform(post("/api/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pagamentoRequestJson.write(PAGAMENTO_REQUEST_ERRO).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver código 201 e o pagamento criado ao enviar dados válidos")
    void realizarPagamento_dadosValidos() throws Exception {

        when(pagamentoService.realizarPagamento(any())).thenReturn(PAGAMENTO_RESPONSE_AUTORIZADO);

        var response = mvc.perform(post("/api/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pagamentoRequestJson.write(PAGAMENTO_REQUEST).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = pagamentoResponseJson.write(PAGAMENTO_RESPONSE_AUTORIZADO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deve devolver código 200 e o pagamento cancelado ao realizar estorno")
    void estornarPagamento() throws Exception {

        when(pagamentoService.realizarEstorno(anyLong()))
                .thenReturn(PAGAMENTO_RESPONSE_CANCELADO);

        var response = mvc.perform(put("/api/pagamentos/1/estorno"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = pagamentoResponseJson.write(PAGAMENTO_RESPONSE_CANCELADO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deve devolver código 404 ao tentar cancelar um pagamento inexistente")
    void estornoPagamento_naoEncontrado() throws Exception {

        when(pagamentoService.realizarEstorno(anyLong()))
                .thenThrow(new EntityNotFoundException("Pagamento não encontrado"));

        var response = mvc.perform(put("/api/pagamentos/999/estorno"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deve devolver código 200 e a transacao ao consultar")
    void listarTransacaoPorId() throws Exception {
        when(pagamentoService.consultarTransacaoPorId(anyLong()))
                .thenReturn(PAGAMENTO_RESPONSE_AUTORIZADO);

        var response = mvc.perform(get("/api/pagamentos/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = pagamentoResponseJson.write(PAGAMENTO_RESPONSE_AUTORIZADO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deve devolver código 200 e a lista de pagamentos ao consultar")
    void listarPagamentos() throws Exception {
        when(pagamentoService.consultarTodasTransacoes())
                .thenReturn(PAGAMENTO_RESPONSE_LIST);

        var response = mvc.perform(get("/api/pagamentos/transacoes"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = pagamentoResponseListJson.write(PAGAMENTO_RESPONSE_LIST).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deve devolver código 200 e a lista paginada de pagamentos ao consultar transações com paginação")
    void consultarPagamentosComPaginacao() throws Exception {

        Page<PagamentoResponse> paginatedResponse =
                new PageImpl<>(PAGAMENTO_RESPONSE_LIST, PageRequest.of(0, 1), 1);

        when(pagamentoService.consultarTodasTransacoesPaginacao(any())).thenReturn(paginatedResponse);

        var response = mvc.perform(get("/api/pagamentos/transacoes/{pages}/inicio/{start}", 1, 0)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
