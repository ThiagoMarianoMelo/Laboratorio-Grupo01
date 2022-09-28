package com.grupo1.app.services.contrato.criacaoDeContrato;

import java.time.LocalDate;

import com.grupo1.app.entidades.bussiness.automoveis.Automovel;
import com.grupo1.app.entidades.bussiness.documentos.contrato.Contrato;
import com.grupo1.app.entidades.bussiness.documentos.pedido.Pedido;
import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

public interface ICriarContratoService {
    Contrato criarContrato(Pedido pedido);
    Contrato criarContrato(
        Usuario usuario,
        Automovel automovel,
        double valor,
        LocalDate dataInico,
        LocalDate dataFim
    );
}
