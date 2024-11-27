package com.banco.api_pagamentos;

import com.banco.api_pagamentos.domain.descricao.Descricao;
import com.banco.api_pagamentos.domain.descricao.dto.DescricaoRequest;
import com.banco.api_pagamentos.domain.descricao.dto.DescricaoResponse;
import com.banco.api_pagamentos.domain.forma_pagamento.FormaPagamento;
import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoResponse;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoRequest;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoResponse;
import com.banco.api_pagamentos.domain.transacao.Transacao;
import com.banco.api_pagamentos.domain.transacao.dto.TransacaoRequest;
import com.banco.api_pagamentos.domain.transacao.dto.TransacaoResponse;
import com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum;
import com.banco.api_pagamentos.domain.transacao_pagamento.TransacaoPagamento;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {

    public static final LocalDateTime DATA_HORA =
            LocalDateTime.parse("01/05/2021 18:30:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    public static final String ESTABELECIMENTO = "Banco CredFacilTest";

    public static final FormaPagamentoRequest FORMA_PAGAMENTO_REQUEST = new FormaPagamentoRequest(
            1L, "AVISTA", 1);

    public static final FormaPagamentoRequest FORMA_PAGAMENTO_REQUEST_ERRO = new FormaPagamentoRequest(
            null, null, 1);

    public static final FormaPagamento FORMA_PAGAMENTO = new FormaPagamento(1L, "AVISTA");

    public static final DescricaoRequest DESCRICAO_REQUEST = new DescricaoRequest(
            BigDecimal.TEN, "01/05/2021 18:30:00", ESTABELECIMENTO);

    public static final DescricaoRequest DESCRICAO_REQUEST_ERRO_DATA = new DescricaoRequest(
            BigDecimal.TEN, "", ESTABELECIMENTO);

    public static final TransacaoRequest TRANSACAO_REQUEST = new TransacaoRequest(
            "4444********1234", 1L, DESCRICAO_REQUEST);

    public static final FormaPagamentoResponse FORMA_PAGAMENTO_RESPONSE = new FormaPagamentoResponse("AVISTA", 1);

    public static final DescricaoResponse DESCRICAO_RESPONSE = new DescricaoResponse(
            BigDecimal.TEN, LocalDateTime.parse("01/05/2021 18:30:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
            ESTABELECIMENTO, "147258369", "147258369", StatusTransacaoEnum.AUTORIZADO);

    public static final DescricaoResponse DESCRICAO_RESPONSE_ESTORNO = new DescricaoResponse(
            BigDecimal.TEN, DATA_HORA, ESTABELECIMENTO, "147258369", "147258369", StatusTransacaoEnum.CANCELADO);

    public static final TransacaoResponse TRANSACAO_RESPONSE = new TransacaoResponse(
            "4444********1234", 1L, DESCRICAO_RESPONSE);

    public static final Descricao DESCRICAO_AUTORIZADA = new Descricao(
            1L, BigDecimal.ONE,DATA_HORA, ESTABELECIMENTO, "147258369", "147258369",
                StatusTransacaoEnum.AUTORIZADO);

    public static final Descricao DESCRICAO_NEGADO = new Descricao(
            1L, BigDecimal.ONE, DATA_HORA, ESTABELECIMENTO, StatusTransacaoEnum.NEGADO);

    public static final Transacao TRANSACAO_AUTORIZADA = new Transacao(
            "4444********1234", 1L, DESCRICAO_AUTORIZADA, List.of());

    public static final Transacao TRANSACAO_NEGADO = new Transacao(
            "1111********1111", 1L, DESCRICAO_NEGADO, List.of());

    public static final TransacaoRequest TRANSACAO_ERRO_DATA = new TransacaoRequest(
            "1111********1111", 1L, DESCRICAO_REQUEST_ERRO_DATA);

    public static final TransacaoResponse TRANSACAO_RESPONSE_ESTORNO = new TransacaoResponse(
            "4444********1234", 1L, DESCRICAO_RESPONSE_ESTORNO);

    public static final PagamentoRequest PAGAMENTO_REQUEST = new PagamentoRequest(
            TRANSACAO_REQUEST, FORMA_PAGAMENTO_REQUEST);

    public static final PagamentoRequest PAGAMENTO_REQUEST_ERRO_DATA = new PagamentoRequest(
            TRANSACAO_ERRO_DATA, FORMA_PAGAMENTO_REQUEST);

    public static final PagamentoRequest PAGAMENTO_REQUEST_ERRO = new PagamentoRequest(
            TRANSACAO_ERRO_DATA, FORMA_PAGAMENTO_REQUEST_ERRO);

    public static final PagamentoResponse PAGAMENTO_RESPONSE_AUTORIZADO = new PagamentoResponse(
            TRANSACAO_RESPONSE, FORMA_PAGAMENTO_RESPONSE);

    public static final PagamentoResponse PAGAMENTO_RESPONSE_CANCELADO = new PagamentoResponse(
            TRANSACAO_RESPONSE_ESTORNO, FORMA_PAGAMENTO_RESPONSE);

    public static final TransacaoPagamento TRANSACAO_PAGAMENTO = new TransacaoPagamento(
            TRANSACAO_AUTORIZADA, FORMA_PAGAMENTO, 1);

    public static final Transacao TRANSACAO = new Transacao(
            "4444********1234", 1L, DESCRICAO_AUTORIZADA, List.of(TRANSACAO_PAGAMENTO));

    public static final List<PagamentoResponse> PAGAMENTO_RESPONSE_LIST = List.of(PAGAMENTO_RESPONSE_AUTORIZADO);
}
