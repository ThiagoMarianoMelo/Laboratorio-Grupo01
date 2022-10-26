package com.backend.backend.models.entities.application;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class ObterVantagemRequest {
    @NotNull
    private UUID vantagemId;
    @NotNull
    private UUID usuarioId;
}
