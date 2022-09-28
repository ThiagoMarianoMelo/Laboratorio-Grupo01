package com.grupo1.app.services.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.app.entidades.application.pedido.CriarPedidoRequest;
import com.grupo1.app.entidades.application.pedido.EditarPedidoRequest;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.documentos.pedido.StatusPedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;
import com.grupo1.app.helpers.DateHelper;
import com.grupo1.app.persistencia.PedidoRepository;
import com.grupo1.app.services.contrato.criacaoDeContrato.ICriarContratoService;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ICriarContratoService criarContratoService;

    @Override
    public Pedido criarPedido(CriarPedidoRequest request, Usuario usuario, Automovel automovel) {
        final Pedido pedido = Pedido.builder()
                .valor(request.getValor())
                .dataInicio(request.getDataInicio())
                .dataFim(request.getDataFim())
                .status(StatusPedido.ANALISE_SOLICITADA)
                .usuario(usuario)
                .automovel(automovel)
                .build();
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> getTodosOsPedidos() {
        final List<Pedido> pedidos = new ArrayList<>();
        pedidoRepository.findAll().forEach(pedidos::add);
        return pedidos;
    }

    @Override
    public Pedido atualizarStatus(Pedido pedido, StatusPedido status) {
        // TODO: Retornar error quando pedidoDeAtualizacaoDeStatusEValido for falso
        if (!pedido.getStatus().equals(status) && pedidoDeAtualizacaoDeStatusEValido(pedido, status)) {
            pedido.setStatus(status);
            final Contrato contrato = criarContrato(pedido);
            if (contrato == null && status.equals(StatusPedido.APROVADO)) {
                // TODO: Melhorar isso aq........
                throw new Error("Erro para criar contrato");
            }
            return pedidoRepository.save(pedido);
        }
        return pedido;
    }

    private boolean pedidoDeAtualizacaoDeStatusEValido(Pedido pedido, StatusPedido novoStatus) {
        if (pedido.getStatus().equals(StatusPedido.CANCELADO)) {
            return !novoStatus.equals(StatusPedido.APROVADO);
        }
        return true;
    }

    private Contrato criarContrato(Pedido pedido) {
        if (pedido.getStatus().equals(StatusPedido.APROVADO)) {
            return criarContratoService.criarContrato(pedido);
        }
        return null;
    }

    @Override
    public Pedido getPedidoById(UUID id) {
        final Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return pedido.get();
        }
        return null;
    }

    @Override
    public Pedido editarPedido(EditarPedidoRequest request, UUID id) {
        final Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            final Pedido pedido = optionalPedido.get();
            if (pedidoDeEdicaoEValido(request, pedido)) {
                pedido.setValor(request.getValor());
                pedido.setDataInicio(request.getDataInico());
                pedido.setDataFim(request.getDataFim());
                return pedidoRepository.save(pedido);
            }
            return null;
        }
        return null;
    }

    private boolean pedidoDeEdicaoEValido(EditarPedidoRequest request, Pedido pedido) {
        final StatusPedido statusDoPedido = pedido.getStatus();
        final boolean datasValidas = DateHelper.dataAMaiorQueDataB(request.getDataFim(), request.getDataInico());
        final boolean statusDoPedidoEValido = !statusDoPedido.equals(StatusPedido.CANCELADO)
                || !statusDoPedido.equals(StatusPedido.APROVADO);
        return datasValidas && statusDoPedidoEValido;
    }

    @Override
    public boolean deletarPedido(UUID id) {
        boolean pedidoDeletado = false;
        try {
            final Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
            if (optionalPedido.isPresent()) {
                pedidoRepository.delete(optionalPedido.get());
                pedidoDeletado = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return pedidoDeletado;
        }
    }

}
