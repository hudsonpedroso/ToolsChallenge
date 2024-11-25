package com.banco.api_pagamentos.domain.forma_pagamento.enums;

import com.banco.api_pagamentos.exceptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoFormaPagamentoEnum {
    AVISTA,

    @JsonProperty("PARCELADO LOJA")
    PARCELADO_LOJA,

    @JsonProperty("PARCELADO EMISSOR")
    PARCELADO_EMISSOR;

    @JsonCreator
    public static TipoFormaPagamentoEnum fromString(String value) {
        for (TipoFormaPagamentoEnum tipo : TipoFormaPagamentoEnum.values()) {
            if (tipo.toString().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new ValidationException("Tipo de pagamento inválido. " +
                "Utilize: AVISTA, PARCELADO LOJA ou PARCELADO EMISSOR");
    }

    @Override
    public String toString() {
        return switch (this) {
            case PARCELADO_LOJA -> "PARCELADO LOJA";
            case PARCELADO_EMISSOR -> "PARCELADO EMISSOR";
            default -> this.name();
        };
    }
}