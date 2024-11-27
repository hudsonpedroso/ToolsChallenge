package com.banco.api_pagamentos.domain.transacao_pagamento;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
public class TransacaoPagamentoId implements Serializable {

    private Long transacaoId;
    private Long formaPagId;
}