package com.banco.api_pagamentos.domain.descricao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DescricaoService {

    private final DescricaoRepository descricaoRepository;

    public void save(Descricao descricao) {
        descricaoRepository.save(descricao);
    }
}