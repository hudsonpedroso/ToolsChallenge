package com.banco.api_pagamentos.domain.pagamento.validacoes.forma_pagamento;

import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoRequest;

public interface ValidadorFormaPagamento {
    void validar(FormaPagamentoRequest formaPag);
}