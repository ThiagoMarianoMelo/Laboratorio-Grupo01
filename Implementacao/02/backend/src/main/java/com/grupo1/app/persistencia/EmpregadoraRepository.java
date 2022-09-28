package com.grupo1.app.persistencia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.app.entidades.bussiness.usuarios.cliente.Empregadora;

@Repository
public interface EmpregadoraRepository extends CrudRepository<Empregadora, String>{
    
}
