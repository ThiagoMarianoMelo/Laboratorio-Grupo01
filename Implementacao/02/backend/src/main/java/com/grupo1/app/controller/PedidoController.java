package com.grupo1.app.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo1.app.entidades.application.pedido.AtualizarStatusRequest;
import com.grupo1.app.entidades.application.pedido.CriarPedidoRequest;
import com.grupo1.app.entidades.application.pedido.EditarPedidoRequest;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;
import com.grupo1.app.services.autenticacao.IAutenticacaoService;
import com.grupo1.app.services.automovel.IAutomovelService;
import com.grupo1.app.services.pedido.IPedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IAutenticacaoService autenticacaoService;

    @Autowired
    private IAutomovelService automovelService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getTodosOsPedidos() {
        final List<Pedido> pedidos = pedidoService.getTodosOsPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(
            @Valid @RequestBody CriarPedidoRequest request) {
        final Usuario usuario = autenticacaoService.getUserById(request.getUserId());
        final Automovel automovel = automovelService.getAutomovelById(request.getAutomovelId());
        if (usuario != null && automovel != null) {
            final Pedido pedido = pedidoService.criarPedido(request, usuario, automovel);
            if (pedido != null) {
                return new ResponseEntity<>(pedido, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Pedido> atualizarStatus(
            @Valid @RequestBody AtualizarStatusRequest request,
            @PathVariable UUID id) {
        final Pedido pedido = pedidoService.getPedidoById(id);
        if (pedido != null) {
            final Pedido pedidoAtualizado = pedidoService.atualizarStatus(pedido, request.getStatus());
            if (pedidoAtualizado != null) {
                return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> editarPedido(
            @Valid @RequestBody EditarPedidoRequest request,
            @PathVariable UUID id) {
        final Pedido pedido = pedidoService.editarPedido(request, id);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> deletarPedido(
            @PathVariable UUID id) {
        if (pedidoService.deletarPedido(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
