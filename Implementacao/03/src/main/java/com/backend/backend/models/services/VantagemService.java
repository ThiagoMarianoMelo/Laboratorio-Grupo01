package com.backend.backend.models.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.models.entities.Cupom;
import com.backend.backend.models.entities.Vantagem;
import com.backend.backend.models.entities.application.CriarVantagemRequest;
import com.backend.backend.models.entities.application.EditarVantagemRequest;
import com.backend.backend.models.entities.usuarios.Aluno;
import com.backend.backend.models.entities.usuarios.EmpresaParceira;
import com.backend.backend.models.entities.usuarios.UsuarioEstudantil;
import com.backend.backend.models.repositories.CupomRepository;
import com.backend.backend.models.repositories.VantagemRepository;

@Service
public class VantagemService {
    @Autowired
    private VantagemRepository vantagemRepository;

    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private EmpresaParceiraService empresaParceiraService;

    public List<Vantagem> getVantagens() {
        return vantagemRepository.findAll();
    }

    public Vantagem criarVantagem(CriarVantagemRequest request) {
        final EmpresaParceira empresaParceira = empresaParceiraService
                .getEmpresaParceira(request.getEmmpresaParceira());
        final Vantagem vantagem = Vantagem.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .custo(request.getValor())
                .empresaParceira(empresaParceira)
                .build();
        vantagemRepository.save(vantagem);
        return vantagem;
    }

    public Vantagem editarVantagem(UUID id, EditarVantagemRequest request) {
        try {
            final Vantagem vantagem = vantagemRepository.findById(id).get();
            if (request.getNome() != null) {
                vantagem.setNome(request.getNome());
            }
            if (request.getDescricao() != null) {
                vantagem.setDescricao(request.getDescricao());
            }
            vantagem.setCusto(request.getCusto());
            vantagemRepository.editarVantagem(vantagem.getNome(), vantagem.getDescricao(), vantagem.getCusto(),
                    vantagem.getId());
            return vantagem;
        } catch (Exception e) {
            return null;
        }

    }

    public Cupom gerarCupom(UUID vantagemId, UsuarioEstudantil usuario) {
        final Vantagem vantagem = getVantagem(vantagemId);
        if (usuario.getSaldo() >= vantagem.getCusto()) {
            final Cupom cupom = Cupom.builder()
                    .aluno((Aluno) usuario)
                    .vantagem(vantagem)
                    .valor(vantagem.getCusto())
                    .build();
            cupomRepository.save(cupom);
            transacaoService.criarTransacaoDeSaida(usuario, cupom, vantagem.getCusto());
            return cupom;
        }
        return null;
    }

    public boolean deletarVantagem(UUID vantagemId) {
        try {
            vantagemRepository.deleteById(vantagemId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Vantagem getVantagem(UUID vantagemId) {
        return vantagemRepository.findById(vantagemId).get();
    }

}
