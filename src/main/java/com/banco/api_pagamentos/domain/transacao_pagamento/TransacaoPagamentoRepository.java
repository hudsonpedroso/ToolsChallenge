package com.banco.api_pagamentos.domain.transacao_pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoPagamentoRepository extends JpaRepository<TransacaoPagamento, Long> {
    TransacaoPagamento findByTransacaoId(Long transacaoId);
}