package com.backend.backend.models.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.models.entities.usuarios.EmpresaParceira;
import com.backend.backend.models.repositories.UsuarioRepository;

@Service
public class EmpresaParceiraService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public EmpresaParceira getEmpresaParceira(UUID id){
        return (EmpresaParceira) usuarioRepository.findById(id).get();
    }
}
