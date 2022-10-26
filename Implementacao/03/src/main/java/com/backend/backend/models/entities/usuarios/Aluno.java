package com.backend.backend.models.entities.usuarios;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Entity
public class Aluno extends UsuarioEstudantil {
    private String rg;
    private String curso;
}
