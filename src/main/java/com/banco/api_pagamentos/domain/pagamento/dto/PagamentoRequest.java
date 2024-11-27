package com.banco.api_pagamentos.domain.pagamento.dto;

import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.banco.api_pagamentos.domain.transacao.dto.TransacaoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PagamentoRequest(

        @NotNull
        @Valid
        TransacaoRequest transacao,

        @NotNull
        @Valid
        FormaPagamentoRequest formaPagamento) {
}