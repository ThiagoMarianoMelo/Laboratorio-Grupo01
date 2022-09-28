package com.grupo1.app.services.contrato;

import java.util.List;
import java.util.UUID;

import com.grupo1.app.entidades.application.contrato.EditarContratoRequest;
import com.grupo1.app.entidades.application.contrato.criarContrato.CriarContratoRequest;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;

public interface IContratoService {
    List<Contrato> obterTodosOsContratos();
    Contrato criaContrato(CriarContratoRequest request);
    Contrato editarContrato(EditarContratoRequest request, UUID id);
    boolean deletarContrato(UUID id);
}
