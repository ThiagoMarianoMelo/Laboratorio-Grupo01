package com.grupo1.app.entidades.bussiness.usuarios.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "Empregadora")
public class Empregadora {
    @Id
    private String id;
    private String nome;

    @ElementCollection
    private List<Double> rendimentosAuferidos = new ArrayList<>();

    @OneToMany(mappedBy = "empregadora")
    private List<Cliente> usuarios;
}
