package com.grupo1.app.services.automovel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.contrato.TipoDeContrato;
import com.grupo1.app.persistencia.AutomovelRepository;

@Service
public class AutomovelService implements IAutomovelService {
    @Autowired
    private AutomovelRepository automovelRepository;

    @Override
    public Automovel getAutomovelById(String id) {
        final Optional<Automovel> automovel = automovelRepository.findById(id);
        if(automovel.isPresent()){
            return automovel.get();
        }
        return null;
    }

    @Override
    public List<Automovel> getTodosOsAutomoveis() {
        List<Automovel> automoveis = new ArrayList<>();
        automovelRepository.findAll().forEach(automoveis::add);
        return automoveis;
    }

    @Override
    public List<Automovel> getAutomoveisAlugados() {
        final LocalDate now = LocalDate.now();
        List<Automovel> automoveis = getTodosOsAutomoveis();
        automoveis = automoveis.stream().filter((automovel) -> automovel.getContratos().stream().anyMatch(
                (contrato) -> contrato.getTipo().equals(TipoDeContrato.BASICO) && contrato.getDataFim().isAfter(now))).collect(Collectors.toList());
        return automoveis;
    }

    @Override
    public List<Automovel> getAutomoveisDisponiveis() {
        final LocalDate now = LocalDate.now();
        List<Automovel> automoveis = getTodosOsAutomoveis();
        automoveis = automoveis.stream().filter((automovel) -> {
            final List<Contrato> contratos = automovel.getContratos();
            if(contratos != null && contratos.size() > 0){
                return contratos.stream().anyMatch(
                    (contrato) -> contrato.getTipo().equals(TipoDeContrato.BASICO) && contrato.getDataFim().isBefore(now)
                );
            }
            return true;
        }).collect(Collectors.toList());
        return automoveis;
    }

}
