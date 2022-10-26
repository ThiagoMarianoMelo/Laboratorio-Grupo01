package com.backend.backend.models.services.usuario;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.backend.models.entities.InstituicaoDeEnsino;
import com.backend.backend.models.entities.application.CadastroBody;
import com.backend.backend.models.entities.application.TipoDeUsuario;
import com.backend.backend.models.entities.usuarios.Aluno;
import com.backend.backend.models.entities.usuarios.Professor;
import com.backend.backend.models.entities.usuarios.Usuario;
import com.backend.backend.models.repositories.UsuarioRepository;

@Service
public abstract class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public final Usuario cadastrar(CadastroBody cadastroBody) {
        try {
            Usuario usuario;
            final InstituicaoDeEnsino instituicaoDeEnsino = (InstituicaoDeEnsino) usuarioRepository.findById(cadastroBody.getInstituicaoDeEnsino()).get();
            if (cadastroBody.getTipoDeUsuario().equals(TipoDeUsuario.ALUNO)) {
                usuario = criarAluno(cadastroBody, instituicaoDeEnsino);
            } else {
                usuario = criarProfessor(cadastroBody, instituicaoDeEnsino);
            }
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw e;
        }

    }

    private Aluno criarAluno(CadastroBody cadastroBody, InstituicaoDeEnsino instituicaoDeEnsino) {
        return Aluno.builder()
                .nome(cadastroBody.getName())
                .email(cadastroBody.getEmail())
                .senha(cadastroBody.getSenha())
                .cpf(cadastroBody.getCpf())
                .curso(cadastroBody.getCurso())
                .instituicaoDeEnsino(instituicaoDeEnsino)
                .build();
    }

    private Professor criarProfessor(CadastroBody cadastroBody, InstituicaoDeEnsino instituicaoDeEnsino) {
        return Professor.builder()
                .nome(cadastroBody.getName())
                .email(cadastroBody.getEmail())
                .senha(cadastroBody.getSenha())
                .cpf(cadastroBody.getCpf())
                .instituicaoDeEnsino(instituicaoDeEnsino)
                .build();
    }

    public final Usuario login(String email, String senha){
        return usuarioRepository.findUserByEmailAndSenha(email, senha);
    }

    public final Usuario getUsuarioById(UUID usuarioId){
        return usuarioRepository.findById(usuarioId).get();
    }

    public final boolean deletarUsuario(UUID id){
        try {
            final Usuario usuario = getUsuarioById(id);
            usuarioRepository.delete(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
