package com.grupo1.app.persistencia;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, UUID> {
    
}
