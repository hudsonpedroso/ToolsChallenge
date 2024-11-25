package com.banco.api_pagamentos.domain.descricao.dto;

import com.banco.api_pagamentos.domain.descricao.Descricao;
import com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DescricaoResponse(

        BigDecimal valor,

        LocalDateTime dataHora,

        String estabelecimento,

        String nsu,

        String codigoAutorizacao,

        StatusTransacaoEnum status) {

        public DescricaoResponse(Descricao descricao) {
            this(descricao.getValor(), descricao.getDataHora(), descricao.getEstabelecimento(),
                    descricao.getNsu(), descricao.getCodigoAutorizacao(), descricao.getStatus());
        }
}