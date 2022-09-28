package com.grupo1.app.persistencia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.app.entidades.bussiness.automoveis.Automovel;

@Repository
public interface AutomovelRepository extends CrudRepository<Automovel, String> {
    
}
