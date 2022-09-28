package com.grupo1.app.services.empregador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.app.entidades.bussiness.usuarios.cliente.Empregadora;
import com.grupo1.app.persistencia.EmpregadoraRepository;

@Service
public class EmpregadoraService implements IEmpregadoraService {
    @Autowired
    private EmpregadoraRepository empregadoraRepository;

    @Override
    public Empregadora getEmpregadoraById(String id) {
        final Optional<Empregadora> empregadora = empregadoraRepository.findById(id);
        if (empregadora.isPresent()) {
            return empregadora.get();
        }
        return null;
    }
}
