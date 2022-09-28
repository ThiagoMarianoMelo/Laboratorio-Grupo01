package com.grupo1.app.entidades.application.pedido;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class EditarPedidoRequest {
    @NotNull
    private double valor;
    @NotNull
    private LocalDate dataInico;
    @NotNull
    private LocalDate dataFim;
    @NotNull
    private UUID automovel;
}
