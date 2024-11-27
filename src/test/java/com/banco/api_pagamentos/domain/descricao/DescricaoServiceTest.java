package com.banco.api_pagamentos.domain.descricao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.banco.api_pagamentos.TestConstants.DESCRICAO_AUTORIZADA;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DescricaoServiceTest {

    @InjectMocks
    private DescricaoService unit;

    @Mock
    private DescricaoRepository descricaoRepository;

    @Test
    void save() {
        assertDoesNotThrow(() -> unit.save(DESCRICAO_AUTORIZADA));
        verify(descricaoRepository).save(any());
    }
}