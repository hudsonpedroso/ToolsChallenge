package com.banco.api_pagamentos.domain.transacao.dto;

import com.banco.api_pagamentos.domain.descricao.dto.DescricaoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TransacaoRequest(

        @NotBlank
        @Pattern(regexp = "\\d{1,4}\\*+\\d{0,4}", message = "deve corresponder ao formato esperado, exemplo: 4444********1234")
        String cartao,

        @NotNull
        @Min(1)
        Long id,

        @NotNull
        @Valid
        DescricaoRequest descricao) {

}