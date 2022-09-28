package com.grupo1.app.persistencia.contrato;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;

// TODO: houve uma tentativa de usar o @Query, rolou não 
// avaliar depois qual era o problema
@Repository
public interface CriarContratoRepository extends CrudRepository<Contrato, UUID>{
}
