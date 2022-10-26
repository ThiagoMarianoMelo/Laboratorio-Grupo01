package com.backend.backend.models.entities.application;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CriarVantagemRequest {
    @NotNull
    private String nome;
    @NotNull
    private String descricao;
    @NotNull
    private double valor;
    @NotNull
    private UUID emmpresaParceira;
}
