package com.grupo1.app.entidades.bussiness.documentos.contrato.contratoBasico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "ContratoBasico")
public class ContratoBasico extends Contrato {
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido", referencedColumnName = "id")
    private Pedido pedido;

    private TipoDeProprietario tipoDeProprietario;
}
