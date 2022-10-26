package com.backend.backend.models.entities.usuarios;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.backend.backend.models.entities.InstituicaoDeEnsino;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Entity
public abstract class UsuarioEstudantil extends Usuario {
    @Setter
    private double saldo;
    private String cpf;
    @OneToOne()
    private InstituicaoDeEnsino instituicaoDeEnsino;
}
