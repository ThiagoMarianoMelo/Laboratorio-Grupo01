package com.grupo1.app.entidades.application.contrato.criarContrato;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ContratoDeCreditoRequest {
    String usuarioId;
    String automovelId;
    Double valor;
    LocalDate dataInicio;
    LocalDate dataFim;
}
