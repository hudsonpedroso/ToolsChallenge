package com.banco.api_pagamentos.domain.transacao;

import com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum.AUTORIZADO;
import static com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum.NEGADO;

@Service
@AllArgsConstructor
public class TransacaoService {

    private static final String CARTAO_NEGADO = "1111********1111";

    private final TransacaoRepository transacaoRepository;

    public Transacao realizarPagamento(Transacao transacao) {
        transacao.getDescricao().setStatus(definirStatus(transacao));
        return transacaoRepository.save(transacao);
    }

    private static StatusTransacaoEnum definirStatus(Transacao transacao) {
        return transacao.getCartao().equals(CARTAO_NEGADO) ? NEGADO : AUTORIZADO;
    }

    @Cacheable(value = "Transacao",
            key = "'DM_PAG_TRANSACAO_0001_GET_BY_ID_' + #id")
    public Transacao consultarPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
    }

    public boolean existsById(Long id) {
        return transacaoRepository.existsById(id);
    }

    public List<Transacao> consultarTodas() {
        return transacaoRepository.findAll();
    }

    public Page<Transacao> consultarTodas(Pageable pageable) {
        return transacaoRepository.findAll(pageable);
    }
}