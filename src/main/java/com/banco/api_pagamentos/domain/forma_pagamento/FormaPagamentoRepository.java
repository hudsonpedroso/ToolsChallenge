package com.banco.api_pagamentos.domain.forma_pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    FormaPagamento findByTipo(String tipo);
}