package com.grupo1.app.entidades.bussiness.usuarios.cliente;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.grupo1.app.entidades.bussiness.documentos.contrato.contratoBasico.ContratoBasico;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@DiscriminatorValue(value = "CLIENTE")
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Cliente extends Usuario{
    private String cpf;
    private String profissao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empregadora", referencedColumnName = "id")
    private Empregadora empregadora;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.EAGER)
    private List<ContratoBasico> contratos;
}
