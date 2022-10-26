package com.backend.backend.models.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.models.entities.Cupom;
import com.backend.backend.models.entities.transacao.TipoDeTransacao;
import com.backend.backend.models.entities.transacao.Transacao;
import com.backend.backend.models.entities.usuarios.UsuarioEstudantil;
import com.backend.backend.models.repositories.TransacaoRepository;
import com.backend.backend.models.services.usuario.UsuarioEstudantilService;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioEstudantilService usuarioEstudantilService;

    public List<Transacao> getTransacoesByUsuarioId(UUID usuarioId) {
        return transacaoRepository.getTransacoesByUsuarioId(usuarioId).get();
    }

    public Transacao criarTransacaoDeSaida(UsuarioEstudantil usuario, Cupom cupom, double valorGasto) {
        final double saldoRestante = usuario.getSaldo() - valorGasto;
        final Transacao transacao = Transacao.builder()
                .usuario(usuario)
                .tipoDeTransacao(TipoDeTransacao.SAIDA)
                .cupom(cupom)
                .valor(cupom.getValor())
                .build();
        atualizarSaldoDoUsuario(saldoRestante, usuario.getId());
        transacaoRepository.save(transacao);
        return transacao;
    }

    public Transacao criarTransacaoDeEntrada(UsuarioEstudantil deUsuario, UsuarioEstudantil paraUsuario, double valor) {
        if (deUsuario.getSaldo() >= valor) {
            final double novoValor = paraUsuario.getSaldo() + valor;
            usuarioEstudantilService.atualizarSaldo(paraUsuario.getId(), novoValor);
            final Transacao transacao = Transacao.builder()
                    .tipoDeTransacao(TipoDeTransacao.ENTRADA)
                    .valor(valor)
                    .usuario(paraUsuario)
                    .build();
            transacaoRepository.save(transacao);
            return transacao;
        }
        return null;
    }

    private void atualizarSaldoDoUsuario(double valor, UUID usuarioId) {
        usuarioEstudantilService.atualizarSaldo(usuarioId, valor);
    }
}
