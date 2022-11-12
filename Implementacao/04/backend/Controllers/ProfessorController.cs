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
[Route("AdcionarSaldo")]
public void addSaldoAluno( [FromBody] AdcionarSaldoAlunoModel modelAdcionarSaldo ){

    DescontarSaldo ds = new DescontarSaldo();

    ds.descontarSaldoConta(modelAdcionarSaldo.valorQueSeraAdcionado, modelAdcionarSaldo.idprofessorFromRequest);

    TrasacaoModel transacaoCriada  =  adcionandoSaldo.adcionarSaldoAluno(modelAdcionarSaldo);

    CadastrarTransacao cadastroDeTransacao = new CadastrarTransacao();

    cadastroDeTransacao.cadastrarTransacao(transacaoCriada);

    }

}
