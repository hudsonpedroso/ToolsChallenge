package com.banco.api_pagamentos.domain.transacao_pagamento;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoPagamentoId implements Serializable {

    private Long transacaoId;
    private Long formaPagId;
}