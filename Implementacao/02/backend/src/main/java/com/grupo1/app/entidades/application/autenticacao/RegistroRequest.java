package com.grupo1.app.entidades.application.autenticacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.grupo1.app.entidades.bussiness.usuarios.TipoDeUsuario;
import lombok.Getter;

@Getter
public class RegistroRequest {
    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private TipoDeUsuario tipo;

    private String cpf;

    private String cnpj;

    private String empregadorId;

    private String profissao;
}
