package com.banco.api_pagamentos.domain.forma_pagamento;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    @Cacheable(
            value = "FormaPagamento",
            key = "'DM_PAG_FORMA_PAGAMENTO_0001_GET_BY_TIPO_' + #tipo")
    public FormaPagamento obterPorTipo(String tipo) {
        return formaPagamentoRepository.findByTipo(tipo);
    }
}