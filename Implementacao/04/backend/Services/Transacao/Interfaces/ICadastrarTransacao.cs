namespace Sprint4.Services.Transacao.Interfaces.ICadastrarTransacao;

using Sprint4.Models.Transacao.TrasacaoModel;

public interface ICadastrarTransacao{

    public int cadastrarTransacao(TrasacaoModel transacao);
}