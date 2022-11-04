namespace Sprint4.Services.Transacao.Interfaces.IListarTransacoes;

using Sprint4.Models.Transacao.listarTransacaoModel;

public interface IListarTransacoes{

    public List<listarTransacaoModel> listarHistorico(int idUsuario);

}