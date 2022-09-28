package com.grupo1.app.services.automovel;

import java.util.List;
import com.grupo1.app.entidades.bussiness.automoveis.Automovel;

public interface IAutomovelService {
    Automovel getAutomovelById(String id);
    List<Automovel> getTodosOsAutomoveis();
    List<Automovel> getAutomoveisAlugados();
    List<Automovel> getAutomoveisDisponiveis();
}
