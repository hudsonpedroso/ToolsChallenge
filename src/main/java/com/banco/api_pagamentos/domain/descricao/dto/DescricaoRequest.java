package com.banco.api_pagamentos.domain.descricao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DescricaoRequest(

        @NotNull(message = "Informar o valor é obrigatório.")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior ou igual a 0.01.")
        @Digits(integer = 12, fraction = 2, message = "O valor deve ter no máximo 12 dígitos inteiros e 2 decimais.")
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal valor,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        String dataHora,

        @NotNull
        @NotBlank
        String estabelecimento) {
}