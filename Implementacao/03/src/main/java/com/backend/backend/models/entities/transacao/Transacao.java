package com.backend.backend.models.entities.transacao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.backend.models.entities.Cupom;
import com.backend.backend.models.entities.usuarios.Usuario;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name = "Transacao")
public class Transacao {
    private UUID id;
    @ManyToOne()
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private TipoDeTransacao tipoDeTransacao;
    private double valor;
    private Cupom cupom;
}
