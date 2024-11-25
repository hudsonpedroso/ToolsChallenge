package com.banco.api_pagamentos.domain.pagamento.dto;

import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoResponse;
import com.banco.api_pagamentos.domain.transacao.Transacao;
import com.banco.api_pagamentos.domain.transacao.dto.TransacaoResponse;

public record PagamentoResponse(

        TransacaoResponse transacao,

        FormaPagamentoResponse formaPagamento) {

    public PagamentoResponse(Transacao transacao, String tipo, Integer parcelas) {
        this(new TransacaoResponse(transacao), new FormaPagamentoResponse(tipo, parcelas));
    }
}