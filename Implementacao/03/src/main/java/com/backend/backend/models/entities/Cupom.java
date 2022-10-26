package com.backend.backend.models.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.backend.models.entities.usuarios.Aluno;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name="Cupom")
public class Cupom {
    private UUID id;
    @ManyToOne()
    private Aluno aluno;
    @ManyToOne()
    private Vantagem vantagem;
    private double valor;
}
