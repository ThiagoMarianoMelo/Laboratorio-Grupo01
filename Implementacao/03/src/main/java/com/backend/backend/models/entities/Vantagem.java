package com.backend.backend.models.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.backend.models.entities.usuarios.EmpresaParceira;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Entity
@Table(name="Vatagem")
public class Vantagem {
    private UUID id;
    @Setter
    private String nome;
    @Setter
    private String descricao;
    @Setter
    private double custo;
    @ManyToOne()
    private EmpresaParceira empresaParceira;
}
