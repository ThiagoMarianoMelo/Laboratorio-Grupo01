package com.grupo1.app.entidades.bussiness.usuarios;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Usuarios")
@NoArgsConstructor
public abstract class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipo;
}