package com.grupo1.app.persistencia;

import com.grupo1.app.entidades.documentos.pedidoPackage.Pedido;

public interface IPedidoRepository{

    public Pedido findByID(String id);
    }