package com.grupo1.app.entidades.usuarios;

import java.util.List;

import com.grupo1.app.entidades.documentos.contratoPackage.Contrato;

public abstract class Usuario {
    private String id;
    private String name;
    private String email;
    private TiposDeUsuario tiposDeUsuario;
    private List<Contrato> contratos;
}