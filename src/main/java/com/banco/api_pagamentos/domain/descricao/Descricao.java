package com.banco.api_pagamentos.domain.descricao;

import com.banco.api_pagamentos.domain.descricao.dto.DescricaoRequest;
import com.banco.api_pagamentos.domain.transacao.enums.StatusTransacaoEnum;
import com.banco.api_pagamentos.exceptions.ValidationException;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.banco.api_pagamentos.domain.descricao.DescricaoConstants.CODIGO_AUTORIZACAO;
import static com.banco.api_pagamentos.domain.descricao.DescricaoConstants.NSU;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "descricao")
@EqualsAndHashCode(of = "id")
public class Descricao implements Serializable {

    @Serial
    private static final long serialVersionUID = 126L; //sonar

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    private String estabelecimento;

    private String nsu;

    @Column(name = "codigo_autorizacao")
    private String codigoAutorizacao;

    @Setter
    @Enumerated(EnumType.STRING)
    private StatusTransacaoEnum status;

    public Descricao (DescricaoRequest request) {

        this.valor = request.valor();
        this.dataHora = obterDataHora(request.dataHora());
        this.estabelecimento = request.estabelecimento();
        this.codigoAutorizacao = CODIGO_AUTORIZACAO;
        this.nsu = NSU;
    }

    private static LocalDateTime obterDataHora(String dataHora) {

        try {
            return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new ValidationException(
                    "É necessário informar a data e hora no formato esperado (dd/MM/yyyy HH:mm:ss)");
        }
    }
}