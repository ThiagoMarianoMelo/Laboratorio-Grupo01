package com.grupo1.app.services.autenticacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.app.entidades.application.autenticacao.RegistroRequest;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;
import com.grupo1.app.entidades.bussiness.usuarios.agente.Banco;
import com.grupo1.app.entidades.bussiness.usuarios.agente.Empresa;
import com.grupo1.app.entidades.bussiness.usuarios.cliente.Cliente;
import com.grupo1.app.entidades.bussiness.usuarios.cliente.Empregadora;
import com.grupo1.app.persistencia.UsuarioRepository;
import com.grupo1.app.services.empregador.IEmpregadoraService;

// TODO: Para instanciar o usuario com o builder, buscar 
// maneira mais elegante doq fazer a mesma coisa multiplas vezes
@Service
public class AutenticacaoService implements IAutenticacaoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IEmpregadoraService empregadoraService;

    @Override
    public Usuario registrar(RegistroRequest registroRequest) {
        switch (registroRequest.getTipo()) {
            case CLIENTE:
                return registrarCliente(registroRequest);
            case EMPRESA:
                return registrarEmpresa(registroRequest);
            case BANCO:
                return registrarBanco(registroRequest);
            default:
                return null;
        }

    }

    private Usuario registrarCliente(RegistroRequest registroRequest) {
        if (registroDeClienteEValido(registroRequest)) {
            final Empregadora empregadora = empregadoraService.getEmpregadoraById(registroRequest.getEmpregadorId());
            final Cliente cliente = Cliente.builder()
                    .id(registroRequest.getCpf())
                    .nome(registroRequest.getNome())
                    .email(registroRequest.getEmail())
                    .senha(registroRequest.getSenha())
                    .tipo(registroRequest.getTipo())
                    .cpf(registroRequest.getCpf())
                    .empregadora(empregadora)
                    .profissao(registroRequest.getProfissao())
                    .build();
            return usuarioRepository.save(cliente);
        }
        return null;
    }

    public Usuario registrarBanco(RegistroRequest registroRequest) {
        if (registroDeBancoEValido(registroRequest)) {
            final Banco banco = Banco.builder()
                    .id(registroRequest.getCnpj())
                    .nome(registroRequest.getNome())
                    .email(registroRequest.getEmail())
                    .senha(registroRequest.getSenha())
                    .cnpj(registroRequest.getCnpj())
                    .tipo(registroRequest.getTipo())
                    .build();
            return usuarioRepository.save(banco);
        }
        return null;
    }

    public Usuario registrarEmpresa(RegistroRequest registroRequest) {
        if (registroDeEmpresaEValido(registroRequest)) {
            final Empresa empresa = Empresa.builder()
                    .id(registroRequest.getCnpj())
                    .nome(registroRequest.getNome())
                    .email(registroRequest.getEmail())
                    .senha(registroRequest.getSenha())
                    .cnpj(registroRequest.getCnpj())
                    .tipo(registroRequest.getTipo())
                    .build();
            return usuarioRepository.save(empresa);
        }
        return null;
    }

    public boolean registroDeClienteEValido(RegistroRequest registroRequest) {
        final Empregadora empregadora = empregadoraService.getEmpregadoraById(registroRequest.getEmpregadorId());
        return registroRequest.getCpf() != null && empregadora != null;
    }

    public boolean registroDeBancoEValido(RegistroRequest registroRequest) {
        return registroRequest.getCnpj() != null;
    }

    public boolean registroDeEmpresaEValido(RegistroRequest registroRequest) {
        return registroRequest.getCnpj() != null;
    }

    @Override
    public Usuario getUsuarioByEmailESenha(String email, String senha) {
        return usuarioRepository.findUserByEmailAndSenha(email, senha);
    }

    @Override
    public Usuario getUserById(String id) {
        final Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        return null;
    }

}
