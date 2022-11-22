namespace Sprint4.Controllers.ProfessorController;

using Microsoft.AspNetCore.Mvc;
using Sprint4.Services.Professor.AdcionarSaldoAluno;
using Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;
using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;
using Sprint4.Services.Transacao.CadastrarTransacao;
using Sprint4.Models.Transacao.TrasacaoModel;
using Sprint4.Services.Usuario.DescontarSaldo;


[ApiController]
public class ProfessorController : ControllerBase 
{
    IAdcionarSaldoAluno adcionandoSaldo;

    public ProfessorController(){

        adcionandoSaldo = new AdcionarSaldo();
    }

[HttpPost]
[Route("enviar-moedas")]
public int enviarMoedasParaAluno( [FromBody] EnviarMoedasModel requisicao ){

    DescontarSaldo ds = new DescontarSaldo();

    ds.descontarSaldoConta(requisicao.valorEmMoedas, requisicao.idProfessor);

    TrasacaoModel transacaoCriada = adcionandoSaldo.adcionarSaldoAluno(requisicao);

    CadastrarTransacao cadastroDeTransacao = new CadastrarTransacao();

    return cadastroDeTransacao.cadastrarTransacao(transacaoCriada);
    }

}
