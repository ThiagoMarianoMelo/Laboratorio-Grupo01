package com.backend.backend.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.backend.models.entities.Cupom;
import com.backend.backend.models.entities.Vantagem;
import com.backend.backend.models.entities.application.CriarVantagemRequest;
import com.backend.backend.models.entities.application.EditarVantagemRequest;
import com.backend.backend.models.entities.application.ObterVantagemRequest;
import com.backend.backend.models.entities.usuarios.UsuarioEstudantil;
import com.backend.backend.models.services.VantagemService;
import com.backend.backend.models.services.usuario.UsuarioService;

@Controller
@RequestMapping("/vantagem")
public class VantagemController {
    @Autowired
    private VantagemService vantagemService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<Vantagem>> listarVantagens() {
        return new ResponseEntity<>(vantagemService.getVantagens(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Vantagem> criariVantagem(
            @Valid @RequestBody CriarVantagemRequest request) {
        final Vantagem vantagem = vantagemService.criarVantagem(request);
        return new ResponseEntity<>(vantagem, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vantagem> editarVantagem(
            @PathVariable UUID id,
            @Valid @RequestBody EditarVantagemRequest request) {
        final Vantagem vantagem = vantagemService.editarVantagem(id, request);
        if (vantagem != null) {
            return new ResponseEntity<>(vantagem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVantagem(
            @PathVariable UUID id) {
        final boolean vantagemDeletada = vantagemService.deletarVantagem(id);
        if (vantagemDeletada) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/obter")
    public ResponseEntity<Cupom> obterVantagem(
            @Valid @RequestBody ObterVantagemRequest request) {
        final UsuarioEstudantil usuario = (UsuarioEstudantil) usuarioService.getUsuarioById(request.getUsuarioId());
        final Cupom cupom = vantagemService.gerarCupom(request.getVantagemId(), usuario);
        return new ResponseEntity<>(cupom, HttpStatus.OK);
    }
}
