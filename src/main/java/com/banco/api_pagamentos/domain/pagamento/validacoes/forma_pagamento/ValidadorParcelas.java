package com.banco.api_pagamentos.domain.pagamento.validacoes.forma_pagamento;

import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.banco.api_pagamentos.domain.forma_pagamento.enums.TipoFormaPagamentoEnum;
import com.banco.api_pagamentos.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import static com.banco.api_pagamentos.domain.forma_pagamento.enums.TipoFormaPagamentoEnum.AVISTA;

@Component
public class ValidadorParcelas implements ValidadorFormaPagamento {

    public void validar(FormaPagamentoRequest formaPag) {

        var tipoPag = TipoFormaPagamentoEnum.fromString(formaPag.tipo());
        var parcelas = formaPag.parcelas();

        if(tipoPag.equals(AVISTA) && parcelas != 1)
            throw new ValidationException("Para pagamento AVISTA o numero de parcelas deve ser IGUAL a UM (1)");

        if(!tipoPag.equals(AVISTA) && parcelas < 2)
            throw new ValidationException("Para pagamento PARCELADO o numero de parcelas deve ser MAIOR que UM (1)");
    }
}