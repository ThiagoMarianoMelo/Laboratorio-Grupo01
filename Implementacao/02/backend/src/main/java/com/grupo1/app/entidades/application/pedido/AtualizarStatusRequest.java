package com.grupo1.app.entidades.application.pedido;

import javax.validation.constraints.NotNull;

import com.grupo1.app.entidades.bussiness.documentos.pedido.StatusPedido;

import lombok.Getter;

@Getter
public class AtualizarStatusRequest {
    @NotNull
    private StatusPedido status;
}
