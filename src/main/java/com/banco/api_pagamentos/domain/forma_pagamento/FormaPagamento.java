package com.banco.api_pagamentos.domain.forma_pagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forma_pagamento")
@EqualsAndHashCode(of = "id")
public class FormaPagamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 125L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
}