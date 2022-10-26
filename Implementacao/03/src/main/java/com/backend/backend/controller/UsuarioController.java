package com.backend.backend.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.backend.models.entities.application.CadastroBody;
import com.backend.backend.models.entities.usuarios.Usuario;
import com.backend.backend.models.services.usuario.UsuarioEstudantilService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioEstudantilService usuarioEstudantilService;

    @PostMapping("")
    public ResponseEntity<Usuario> cadastro(
            @Valid @RequestBody CadastroBody request) {
        Usuario usuario = null;

        switch (request.getTipoDeUsuario()) {
            case ALUNO:
            case PROFESSOR:
                usuario = usuarioEstudantilService.cadastrar(request);
                break;
            default:
                break;
        }

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Usuario> login(
        @RequestParam(required = true) String email,
        @RequestParam(required = true) String password
    ){
        final Usuario usuarioLogado = usuarioEstudantilService.login(email, password);
        return new ResponseEntity<>(usuarioLogado , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(
        @PathVariable UUID id
    ){
        if(usuarioEstudantilService.deletarUsuario(id)){
            return new ResponseEntity<Boolean>(HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
