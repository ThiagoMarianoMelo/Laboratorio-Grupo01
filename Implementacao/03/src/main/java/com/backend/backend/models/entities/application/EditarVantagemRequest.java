package com.backend.backend.models.entities.application;

import javax.validation.constraints.Min;

import lombok.Getter;

@Getter
public class EditarVantagemRequest {
    private String nome;
    private String descricao;
    @Min(value = 0)
    private double custo;
}
