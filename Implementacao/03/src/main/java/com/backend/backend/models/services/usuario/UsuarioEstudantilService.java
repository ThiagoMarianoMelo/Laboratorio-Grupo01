package com.backend.backend.models.services.usuario;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.models.repositories.UsuarioRepository;

@Service
public class UsuarioEstudantilService extends UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void atualizarSaldo(UUID usuarioId, double valor){
        usuarioRepository.atualizarSaldo(valor, usuarioId);
    }
}
