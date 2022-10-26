package com.backend.backend.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.backend.models.entities.application.DarMoedasRequest;
import com.backend.backend.models.entities.application.ExtratoResponse;
import com.backend.backend.models.entities.transacao.Transacao;
import com.backend.backend.models.entities.usuarios.UsuarioEstudantil;
import com.backend.backend.models.services.TransacaoService;
import com.backend.backend.models.services.usuario.UsuarioService;

@Controller
@RequestMapping("")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/extrato/{usuarioId}")
    public ResponseEntity<ExtratoResponse> consultarExtrato(
            @PathVariable UUID usuarioId) {
        final List<Transacao> transacoes = transacaoService.getTransacoesByUsuarioId(usuarioId);
        final UsuarioEstudantil usuario = (UsuarioEstudantil) usuarioService.getUsuarioById(usuarioId);
        final ExtratoResponse extratoResponse = ExtratoResponse.builder()
                .transacoes(transacoes)
                .saldo(usuario.getSaldo())
                .build();
        return new ResponseEntity<>(extratoResponse, HttpStatus.OK);
    }

    @PostMapping("/dar")
    public ResponseEntity<Transacao> darMoedas(
        @Valid @RequestBody DarMoedasRequest request
    ){
        final UsuarioEstudantil deUsuario = (UsuarioEstudantil) usuarioService.getUsuarioById(request.getDeId());
        final UsuarioEstudantil paraUsuario = (UsuarioEstudantil) usuarioService.getUsuarioById(request.getParaId());
        final Transacao transacao = transacaoService.criarTransacaoDeEntrada(deUsuario, paraUsuario, request.getValor());
        return new ResponseEntity<>(transacao, HttpStatus.OK);
    }
}
