
public enum TipoDisciplina {
    OBRIGATORIA(4),
    OPCIONAL(2);

    int quantidadeDeMateriasPossiveis;
    TipoDisciplina(int quantidadeDeMateriasPossiveis){
        this.quantidadeDeMateriasPossiveis = quantidadeDeMateriasPossiveis;
    }

}
