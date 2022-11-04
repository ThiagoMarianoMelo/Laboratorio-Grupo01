namespace Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;

using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;
using Sprint4.Models.Transacao.TrasacaoModel;

public interface IAdcionarSaldoAluno{

    public TrasacaoModel adcionarSaldoAluno(AdcionarSaldoAlunoModel adcionarSaldoAlunoModel);

}