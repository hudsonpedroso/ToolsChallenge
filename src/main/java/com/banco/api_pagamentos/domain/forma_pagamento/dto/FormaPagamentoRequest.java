package com.banco.api_pagamentos.domain.forma_pagamento.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FormaPagamentoRequest(

        @Nullable
        Long id,

        @NotNull
        @NotBlank
        String tipo,

        @NotNull
        @Min(value = 1, message = "O numero de parcelas valor deve ser maior ou igual a 1.")
        @Max(value = 24, message = "Numero máximo de parcelas permitido é 24.")
        Integer parcelas) {
}