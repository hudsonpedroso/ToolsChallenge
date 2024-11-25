package com.banco.api_pagamentos.domain.transacao_pagamento;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoPagamentoId implements Serializable {

    private Long transacaoId;
    private Long formaPagId;
}