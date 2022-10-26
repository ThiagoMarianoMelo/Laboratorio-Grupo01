package com.backend.backend.models.entities.application;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class DarMoedasRequest {
    @NotNull
    private UUID deId;
    @NotNull
    private UUID paraId;
    @NotNull
    private double valor;
}
