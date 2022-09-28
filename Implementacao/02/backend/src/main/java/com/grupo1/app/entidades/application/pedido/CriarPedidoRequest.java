package com.grupo1.app.entidades.application.pedido;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CriarPedidoRequest {
    @NotNull
    private double valor;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    private String automovelId;

    @NotNull
    private String userId;
}
