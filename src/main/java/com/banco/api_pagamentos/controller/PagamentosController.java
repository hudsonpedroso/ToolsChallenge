package com.banco.api_pagamentos.controller;

import com.banco.api_pagamentos.domain.pagamento.PagamentoService;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoRequest;
import com.banco.api_pagamentos.domain.pagamento.dto.PagamentoResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagamentos")
@AllArgsConstructor
public class PagamentosController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponse> realizarPagamento(@RequestBody @Valid PagamentoRequest request) {
        var response = pagamentoService.realizarPagamento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/estorno")
    public ResponseEntity<PagamentoResponse> realizarEstorno(@PathVariable Long id) {
        var response = pagamentoService.realizarEstorno(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> consultarTransacao(@PathVariable Long id) {
        var response = pagamentoService.consultarTransacaoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transacoes")
    public ResponseEntity<List<PagamentoResponse>> consultarTodasTransacoes() {
        return ResponseEntity.ok(pagamentoService.consultarTodasTransacoes());
    }

    @GetMapping("/transacoes/{pages}/inicio/{start}")
    public ResponseEntity<Page<PagamentoResponse>> consultarTodasTransacoesPaginacao(
            @PathVariable Integer pages,
            @PathVariable Integer start) {
        var pageable = PageRequest.of(start, pages);
        var page = pagamentoService.consultarTodasTransacoesPaginacao(pageable);
        return ResponseEntity.ok(page);
    }
}