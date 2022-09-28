package com.grupo1.app.entidades.bussiness.documentos.contrato;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Contratos")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public abstract class Contrato {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Setter
    private double valor;

    @Setter
    private LocalDate dataInicio;

    @Setter
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    private TipoDeContrato tipo;

    @ManyToOne
    @JoinColumn(name = "automovel", referencedColumnName = "id")
    private Automovel automovel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proprietario", referencedColumnName = "id")
    private Usuario proprietario;
}
