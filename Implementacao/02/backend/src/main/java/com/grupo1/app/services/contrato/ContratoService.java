package com.grupo1.app.services.contrato;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.app.entidades.application.contrato.EditarContratoRequest;
import com.grupo1.app.entidades.application.contrato.criarContrato.ContratoBasicoRequest;
import com.grupo1.app.entidades.application.contrato.criarContrato.ContratoDeCreditoRequest;
import com.grupo1.app.entidades.application.contrato.criarContrato.CriarContratoRequest;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.contrato.TipoDeContrato;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.usuarios.TipoDeUsuario;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;
import com.grupo1.app.helpers.DateHelper;
import com.grupo1.app.persistencia.contrato.ContratoRepository;
import com.grupo1.app.services.autenticacao.IAutenticacaoService;
import com.grupo1.app.services.automovel.IAutomovelService;
import com.grupo1.app.services.contrato.criacaoDeContrato.ICriarContratoService;
import com.grupo1.app.services.pedido.IPedidoService;

@Service
public class ContratoService implements IContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ICriarContratoService criarContratoService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IAutenticacaoService autenticacaoService;

    @Autowired
    private IAutomovelService automovelService;

    @Override
    public List<Contrato> obterTodosOsContratos() {
        List<Contrato> contratos = new ArrayList<>();
        contratoRepository.findAll().forEach(contratos::add);
        return contratos;
    }

    @Override
    public Contrato criaContrato(CriarContratoRequest request) {
        final TipoDeContrato tipoDeContrato = request.getTipoDeContrato();
        if (tipoDeContrato.equals(TipoDeContrato.BASICO) && pedidoDeCriacaoDeContratoBasicoEValido(request)) {
            return criarContratoBasico(request.getContratatoBasicoRequest());
        }
        if (pedidoDeCriacaoDeContratoDeCreditoEValido(request)) {
            return criarContratoDeCredito(request.getContratoDeCreditoRequest());
        }
        return null;
    }

    private Contrato criarContratoBasico(ContratoBasicoRequest request) {
        final boolean oContratoJaEstaFeito = contratoRepository.obterContratoPorPedidoId(request.getPedidoId()) != null;
        if (!oContratoJaEstaFeito) {
            final Pedido pedido = pedidoService.getPedidoById(request.getPedidoId());
            if (pedido != null) {
                return criarContratoService.criarContrato(pedido);
            }
            return null;
        }
        return null;
    }

    private Contrato criarContratoDeCredito(ContratoDeCreditoRequest request) {
        final Automovel automovel = automovelService.getAutomovelById(request.getAutomovelId());
        final Usuario usuario = autenticacaoService.getUserById(request.getUsuarioId());
        if (automovel != null && usuario != null && usuario.getTipo().equals(TipoDeUsuario.BANCO)) {
            return criarContratoService.criarContrato(
                    usuario,
                    automovel,
                    request.getValor(),
                    request.getDataInicio(),
                    request.getDataFim());
        }
        return null;
    }

    private boolean pedidoDeCriacaoDeContratoBasicoEValido(CriarContratoRequest request) {
        return request.getContratatoBasicoRequest().getPedidoId() != null;
    }

    private boolean pedidoDeCriacaoDeContratoDeCreditoEValido(CriarContratoRequest request) {
        final ContratoDeCreditoRequest contratoDeCreditoRequest = request.getContratoDeCreditoRequest();
        return contratoDeCreditoRequest.getAutomovelId() != null &&
                contratoDeCreditoRequest.getUsuarioId() != null &&
                contratoDeCreditoRequest.getDataFim() != null &&
                contratoDeCreditoRequest.getDataInicio() != null &&
                contratoDeCreditoRequest.getValor() != null &&
                contratoDeCreditoRequest.getValor() > 0;
    }

    @Override
    public Contrato editarContrato(EditarContratoRequest request, UUID id) {
        // TODO: Retornar erro quando pedidoDeEdicaoDeContratoEValido é false
        if (pedidoDeEdicaoDeContratoEValido(request)) {
            final Optional<Contrato> optionalContrato = contratoRepository.findById(id);
            if (optionalContrato.isPresent()) {
                final Contrato contrato = optionalContrato.get();
                contrato.setValor(request.getValor());
                contrato.setDataInicio(request.getDataInicio());
                contrato.setDataFim(request.getDataFim());
                return contratoRepository.save(contrato);
            }
            return null;
        }
        return null;
    }

    private boolean pedidoDeEdicaoDeContratoEValido(EditarContratoRequest request) {
        return DateHelper.dataAMaiorQueDataB(request.getDataFim(), request.getDataInicio());
    }

    @Override
    public boolean deletarContrato(UUID id) {
        boolean estaDeletado = false;
        try {
            final Optional<Contrato> optionalContrato = contratoRepository.findById(id);
            if (optionalContrato.isPresent()) {
                contratoRepository.delete(optionalContrato.get());
                estaDeletado = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return estaDeletado;
        }
    }
}
