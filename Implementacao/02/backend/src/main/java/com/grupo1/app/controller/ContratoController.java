package com.grupo1.app.controller;

import java.time.LocalDate;
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

import com.grupo1.app.entidades.application.contrato.EditarContratoRequest;
import com.grupo1.app.entidades.application.contrato.criarContrato.CriarContratoRequest;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.services.contrato.IContratoService;

@RestController()
@RequestMapping("/contrato")
public class ContratoController {
    @Autowired
    private IContratoService contratoService;

    @GetMapping
    public ResponseEntity<List<Contrato>> getTodosOsContratos() {
        final List<Contrato> contratos = contratoService.obterTodosOsContratos();
        return new ResponseEntity<>(contratos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contrato> criarContrato(
        @Valid @RequestBody CriarContratoRequest request
    ){
        final Contrato contrato = contratoService.criaContrato(request);
        if(contrato == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(contrato,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Contrato> editarContrato(
      @Valid  @RequestBody EditarContratoRequest request,
      @PathVariable UUID id
    ){
         final Contrato contrato = contratoService.editarContrato(request, id);
         if(contrato != null){
            return new ResponseEntity<>(contrato, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Contrato> editarContrato(
      @PathVariable UUID id
    ){
         if(contratoService.deletarContrato(id)){
            return new ResponseEntity<Contrato>(HttpStatus.OK);
         }
         return new ResponseEntity<Contrato>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
