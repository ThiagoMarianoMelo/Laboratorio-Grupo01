package com.grupo1.app.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.grupo1.app.entidades.Cliente;

@RestController
public interface clienteRepository extends JpaRepository<Cliente, String>{    
}
