package com.backend.backend.models.entities.application;

import java.util.List;

import com.backend.backend.models.entities.transacao.Transacao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExtratoResponse {
    private List<Transacao> transacoes;
    private double saldo;
}
