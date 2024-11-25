package com.banco.api_pagamentos.exceptions;

public class HttpNotFoundException extends RuntimeException {
    public HttpNotFoundException(String mensagem) {
        super(mensagem);
    }
}