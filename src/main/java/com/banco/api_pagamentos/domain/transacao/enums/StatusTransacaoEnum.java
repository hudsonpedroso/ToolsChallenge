package com.banco.api_pagamentos.domain.transacao.enums;

public enum StatusTransacaoEnum {

    AUTORIZADO,
    CANCELADO,
    NEGADO;

    public boolean isAutorizado() {
        return AUTORIZADO.equals(this);
    }
}