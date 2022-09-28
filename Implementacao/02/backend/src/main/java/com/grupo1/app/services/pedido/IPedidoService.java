package com.grupo1.app.services.pedido;

import java.util.List;
import java.util.UUID;

import com.grupo1.app.entidades.application.pedido.CriarPedidoRequest;
import com.grupo1.app.entidades.application.pedido.EditarPedidoRequest;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.documentos.pedido.StatusPedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

public interface IPedidoService {
    Pedido getPedidoById(UUID id);
    List<Pedido> getTodosOsPedidos();
    Pedido atualizarStatus(Pedido pedido, StatusPedido status);
    Pedido criarPedido(CriarPedidoRequest request, Usuario usuario, Automovel automovel);
    Pedido editarPedido(EditarPedidoRequest request, UUID id);
    boolean deletarPedido(UUID id);
}
