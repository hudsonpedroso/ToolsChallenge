package com.banco.api_pagamentos.domain.forma_pagamento.dto;

public record FormaPagamentoResponse(

        String tipo,

        Integer parcelas) {
}