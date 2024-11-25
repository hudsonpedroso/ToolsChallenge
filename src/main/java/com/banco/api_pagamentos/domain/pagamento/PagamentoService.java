package com.banco.api_pagamentos.domain.pagamento;

import com.banco.api_pagamentos.domain.descricao.DescricaoService;
import com.banco.api_pagamentos.domain.forma_pagamento.FormaPagamentoService;
import com.banco.api_pagamentos.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoRequest;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoResponse;
import com.banco.api_pagamentos.domain.pagamento.validacoes.forma_pagamento.ValidadorFormaPagamento;
import com.banco.api_pagamentos.domain.transacao.Transacao;
import com.banco.api_pagamentos.domain.transacao.TransacaoService;
import com.banco.api_pagamentos.domain.transacao_pagamento.TransacaoPagamento;
import com.banco.api_pagamentos.domain.transacao_pagamento.TransacaoPagamentoService;
import com.banco.api_pagamentos.exceptions.HttpNotFoundException;
import com.banco.api_pagamentos.exceptions.ValidationException;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum.CANCELADO;
import static java.lang.String.format;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final TransacaoService transacaoService;
    private final TransacaoPagamentoService transacaoPagamentoService;
    private final FormaPagamentoService formaPagamentoService;
    private final DescricaoService descricaoService;

    private final List<ValidadorFormaPagamento> validadoresFormaPagamento;

    @Transactional
    public PagamentoResponse realizarPagamento(PagamentoRequest request) {

        var requestTransacao = request.transacao();
        var requestFormaPag = request.formaPagamento();
        var parcelas = requestFormaPag.parcelas();

        validarTransacao(requestTransacao.id());
        validarFormaPagamento(requestFormaPag);

        var novaTransacao = transacaoService.realizarPagamento(new Transacao(requestTransacao));
        var formaPagamento = formaPagamentoService.obterPorTipo(requestFormaPag.tipo());

        var transacaoPagamento = new TransacaoPagamento(novaTransacao, formaPagamento, parcelas);
        transacaoPagamentoService.save(transacaoPagamento);

        return new PagamentoResponse(novaTransacao, formaPagamento.getTipo(), parcelas);
    }

    @Transactional
    public PagamentoResponse realizarEstorno(Long id) {

        var transacao = transacaoService.consultarPorId(id);
        var descricao = transacao.getDescricao();
        var status = descricao.getStatus();

        if(!status.isAutorizado())
            throw new ValidationException(
                    format("Impossivel extornar pagamento com status NEGADO. Consultar Transacao id: %s", id));


        descricao.setStatus(CANCELADO);
        descricaoService.save(descricao);

        var transacaoPag = transacao.getTransacoesPagamentos().getFirst();
        var formaPag = transacaoPag.getFormaPagamento();
        var parcelas = transacaoPag.getParcelas();

        return new PagamentoResponse(transacao, formaPag.getTipo(), parcelas);
    }

    public PagamentoResponse consultarTransacaoPorId(Long id) {
        var transacao = transacaoService.consultarPorId(id);
        var transacaoPag = transacao.getTransacoesPagamentos().getFirst();
        var formaPag = transacaoPag.getFormaPagamento();
        var parcelas = transacaoPag.getParcelas();

        return new PagamentoResponse(transacao, formaPag.getTipo(), parcelas);
    }

    public List<PagamentoResponse> consultarTodasTransacoes() {

        var transacoes = transacaoService.consultarTodas().stream()
                .map(transacao -> {
                    var transacaoPag = transacao.getTransacoesPagamentos().getFirst();
                    var formaPag = transacaoPag.getFormaPagamento();
                    var parcelas = transacaoPag.getParcelas();

                    return new PagamentoResponse(transacao, formaPag.getTipo(), parcelas);
                }).toList();

        if(transacoes.isEmpty())
            throw new HttpNotFoundException("Não existem transações registradas");

        return transacoes;
    }


    public Page<PagamentoResponse> consultarTodasTransacoesPaginacao(Pageable paginacao) {

        var transacoes = transacaoService.consultarTodas(paginacao)
                .map(transacao -> {
                    var transacaoPag = transacao.getTransacoesPagamentos().getFirst();
                    var formaPag = transacaoPag.getFormaPagamento();
                    var parcelas = transacaoPag.getParcelas();

                    return new PagamentoResponse(transacao, formaPag.getTipo(), parcelas);
                });

        if(transacoes.isEmpty())
            throw new HttpNotFoundException("Não existem transações registradas");

        return transacoes;
    }

    private void validarTransacao(Long transacaoId) {
        if(transacaoService.existsById(transacaoId))
            throw new EntityExistsException(
                    format("Transação de pagamento já foi realizada para o id %s." +
                            "É necessário efetuar uma nova transação com um novo id.", transacaoId));
    }

    private void validarFormaPagamento(FormaPagamentoRequest requestFormaPag) {
        validadoresFormaPagamento.forEach(v -> v.validar(requestFormaPag));
    }
}