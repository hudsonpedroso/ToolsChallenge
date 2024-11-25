package com.banco.api_pagamentos.domain.transacao.dto;

import com.banco.api_pagamentos.domain.descricao.dto.DescricaoResponse;
import com.banco.api_pagamentos.domain.transacao.Transacao;

public record TransacaoResponse(

        String cartao,

        Long id,

        DescricaoResponse descricao) {

        public TransacaoResponse (Transacao transacao) {
                this(transacao.getCartao(), transacao.getId(), new DescricaoResponse(transacao.getDescricao()));
        }
}