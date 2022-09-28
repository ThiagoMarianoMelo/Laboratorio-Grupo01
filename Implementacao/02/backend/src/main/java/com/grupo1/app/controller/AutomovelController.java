package com.grupo1.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo1.app.entidades.application.automovel.TipoDeObtencaoDeAutomovel;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.services.automovel.IAutomovelService;

@RestController
@RequestMapping("/automovel")
public class AutomovelController {

    @Autowired
    private IAutomovelService automovelService;

    @GetMapping
    public ResponseEntity<List<Automovel>> getAutomoveis(
            @RequestParam(required = false) TipoDeObtencaoDeAutomovel tipoDeObtencaoDeAutomovel) {
        List<Automovel> automoveis = new ArrayList<>();
        final boolean eParaObterAlgumTipoEspecifico = tipoDeObtencaoDeAutomovel != null;
        if (eParaObterAlgumTipoEspecifico) {
            if (tipoDeObtencaoDeAutomovel.equals(TipoDeObtencaoDeAutomovel.ALUGADOS)) {
                automoveis = automovelService.getAutomoveisAlugados();
            } else {
                automoveis = automovelService.getAutomoveisDisponiveis();
            }
        } else {
            automoveis = automovelService.getTodosOsAutomoveis();
        }
        if (automoveis != null) {
            return new ResponseEntity<>(automoveis, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
