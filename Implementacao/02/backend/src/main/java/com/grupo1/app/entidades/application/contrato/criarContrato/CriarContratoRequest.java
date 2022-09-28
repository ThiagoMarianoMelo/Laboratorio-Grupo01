package com.grupo1.app.entidades.application.contrato.criarContrato;

import javax.validation.constraints.NotNull;

import com.grupo1.app.entidades.bussiness.documentos.contrato.TipoDeContrato;

import lombok.Getter;

@Getter
public class CriarContratoRequest {
    @NotNull
    TipoDeContrato tipoDeContrato;

    ContratoBasicoRequest contratatoBasicoRequest;

    ContratoDeCreditoRequest contratoDeCreditoRequest;
}
