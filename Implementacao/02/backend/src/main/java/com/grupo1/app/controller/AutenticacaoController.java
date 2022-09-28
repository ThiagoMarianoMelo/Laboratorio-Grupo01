package com.grupo1.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo1.app.entidades.application.autenticacao.LoginRequest;
import com.grupo1.app.entidades.application.autenticacao.RegistroRequest;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;
import com.grupo1.app.services.autenticacao.IAutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private IAutenticacaoService autenticacaoService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(
            @Valid @RequestBody RegistroRequest request) {
            final Usuario usuario = autenticacaoService.registrar(request);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(
            @Valid @RequestBody LoginRequest request) {
        final Usuario usuario = autenticacaoService.getUsuarioByEmailESenha(
                request.getEmail(), request.getSenha());
        if (usuario != null) {
            // TODO: retornar JWT e menos infos sobre o usuario
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
