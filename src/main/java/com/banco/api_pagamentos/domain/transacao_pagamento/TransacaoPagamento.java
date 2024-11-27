package com.banco.api_pagamentos.domain.transacao_pagamento;

import com.banco.api_pagamentos.domain.forma_pagamento.FormaPagamento;
import com.banco.api_pagamentos.domain.transacao.Transacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "TransacaoPagamento")
public class TransacaoPagamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 123L; 

    @EmbeddedId
    private TransacaoPagamentoId id;

    @MapsId("transacaoId")
    @ManyToOne
    @JoinColumn(name = "transacao_id")
    private Transacao transacao;

    @MapsId("formaPagId")
    @ManyToOne
    @JoinColumn(name = "forma_pag_id")
    private FormaPagamento formaPagamento;

    private Integer parcelas;

    public TransacaoPagamento(Transacao transacao, FormaPagamento formaPag, Integer parcelas) {
        this(
                new TransacaoPagamentoId(transacao.getId(), formaPag.getId()),
                transacao,
                formaPag,
                parcelas
        );
    }
}