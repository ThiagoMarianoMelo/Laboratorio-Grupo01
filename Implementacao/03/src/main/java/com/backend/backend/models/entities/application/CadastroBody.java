package com.backend.backend.models.entities.application;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CadastroBody {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String senha;
    @NotNull
    private TipoDeUsuario tipoDeUsuario;
    private UUID instituicaoDeEnsino;
    private String cpf;
    private String cnpj;
    private String rg;
    private String curso;
}
