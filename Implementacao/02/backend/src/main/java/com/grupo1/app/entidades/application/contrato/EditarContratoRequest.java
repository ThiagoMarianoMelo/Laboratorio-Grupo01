package com.grupo1.app.entidades.application.contrato;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class EditarContratoRequest {
    @NotNull
    private double valor;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataFim;
}
