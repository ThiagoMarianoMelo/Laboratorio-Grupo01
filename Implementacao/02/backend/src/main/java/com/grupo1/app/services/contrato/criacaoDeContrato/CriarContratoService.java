package com.grupo1.app.services.contrato.criacaoDeContrato;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.contrato.ContratoDeCredito;
import com.grupo1.app.entidades.bussiness.documentos.contrato.TipoDeContrato;
import com.grupo1.app.entidades.bussiness.documentos.contrato.contratoBasico.ContratoBasico;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;
import com.grupo1.app.persistencia.contrato.CriarContratoRepository;

@Service
public class CriarContratoService implements ICriarContratoService {

        @Autowired
        private CriarContratoRepository criarContratoRepository;

        @Override
        public Contrato criarContrato(Pedido pedido) {
                final ContratoBasico contrato = ContratoBasico.builder()
                                .valor(pedido.getValor())
                                .tipo(TipoDeContrato.BASICO)
                                .dataInicio(pedido.getDataInicio())
                                .dataFim(pedido.getDataFim())
                                .automovel(pedido.getAutomovel())
                                .proprietario(pedido.getUsuario())
                                .pedido(pedido)
                                .build();

                return criarContratoRepository.save(contrato);
        }

        @Override
        public Contrato criarContrato(
                        Usuario usuario,
                        Automovel automovel,
                        double valor,
                        LocalDate dataInico,
                        LocalDate dataFim) {
                final Contrato contrato = ContratoDeCredito.builder()
                                .valor(valor)
                                .tipo(TipoDeContrato.CREDITO)
                                .dataInicio(dataInico)
                                .dataFim(dataFim)
                                .automovel(automovel)
                                .proprietario(usuario)
                                .build();

                return criarContratoRepository.save(contrato);
        }

}
