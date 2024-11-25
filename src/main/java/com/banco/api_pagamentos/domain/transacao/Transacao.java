package com.banco.api_pagamentos.domain.transacao;

import com.banco.api_pagamentos.domain.descricao.Descricao;
import com.banco.api_pagamentos.domain.transacao.dto.TransacaoRequest;
import com.banco.api_pagamentos.domain.transacao_pagamento.TransacaoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transacao")
@EqualsAndHashCode(of = "id")
public class Transacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 124L; //sonar

    @Column(nullable = false)
    private String cartao;

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "descricao_id", referencedColumnName = "id")
    private Descricao descricao;

    @OneToMany(mappedBy = "transacao")
    private List<TransacaoPagamento> transacoesPagamentos;

    public Transacao(TransacaoRequest request) {

        this.id = request.id();
        this.cartao = request.cartao();
        this.descricao = new Descricao(request.descricao());
    }
}