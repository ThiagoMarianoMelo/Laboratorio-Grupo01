package com.grupo1.app.persistencia.contrato;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;

@Repository
public interface ContratoRepository extends CriarContratoRepository, CrudRepository<Contrato, UUID>{
    @Query("select c from Contrato c where c.pedido = ?1")
    public Contrato obterContratoPorPedidoId(UUID pedidoId);
}
