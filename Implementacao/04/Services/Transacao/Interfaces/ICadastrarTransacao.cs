namespace Sprint4.Services.Transacao.Interfaces.ICadastrarTransacao;

using Sprint4.Models.Transacao.TrasacaoModel;

public interface ICadastrarTransacao{

    public void cadastrarTransacao(TrasacaoModel transacao);
}