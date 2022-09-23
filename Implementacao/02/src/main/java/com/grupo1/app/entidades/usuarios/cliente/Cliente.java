package com.grupo1.app.entidades.usuarios.cliente;

import java.util.List;

import com.grupo1.app.entidades.documentos.pedidoPackage.Pedido;
import com.grupo1.app.entidades.usuarios.Usuario;

public class Cliente extends Usuario {
    private String cpf;
    private String profissao;
    private Empregadora empregadora;
    private List<Pedido> pedidos;
}