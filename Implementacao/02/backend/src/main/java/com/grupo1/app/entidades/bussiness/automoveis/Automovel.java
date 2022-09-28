package com.grupo1.app.entidades.bussiness.automoveis;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;

import lombok.Getter;

@Entity
@Getter
@Table(name = "automoveis")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Automovel {
    @Id
    private String id;
    private String matricula;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;
    @OneToMany(mappedBy = "automovel", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    @OneToMany(mappedBy = "automovel", fetch = FetchType.EAGER)
    private List<Contrato> contratos;

}
