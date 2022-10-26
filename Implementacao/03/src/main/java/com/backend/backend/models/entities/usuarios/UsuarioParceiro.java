package com.backend.backend.models.entities.usuarios;

import javax.persistence.Entity;

@Entity
public abstract class UsuarioParceiro extends Usuario {
    private String cnpj;
}
