package com.grupo1.app.entidades.bussiness.documentos.contrato;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue(value = "ContratoDeCredito")
public class ContratoDeCredito extends Contrato {
}
