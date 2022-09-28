package com.grupo1.app.entidades.bussiness.usuarios.agente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;

import lombok.experimental.SuperBuilder;

@SuperBuilder
@DiscriminatorValue(value = "EMPRESA")
public class Empresa extends Agente{
    
}
