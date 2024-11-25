package com.banco.api_pagamentos.domain.transacao_pagamento;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransacaoPagamentoService {

    private final TransacaoPagamentoRepository transacaoPagamentoRepository;

    public void save(TransacaoPagamento tpag) {
        transacaoPagamentoRepository.save(tpag);
    }
}