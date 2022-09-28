package com.grupo1.app.entidades.bussiness.usuarios.agente;

import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Agente extends Usuario {
    private String cnpj;
}
