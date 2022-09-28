package com.grupo1.app.services.autenticacao;

import com.grupo1.app.entidades.application.autenticacao.RegistroRequest;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

public interface IAutenticacaoService {
    Usuario registrar(RegistroRequest registroRequest);
    Usuario getUsuarioByEmailESenha(String email, String senha);
    Usuario getUserById(String id);
}
