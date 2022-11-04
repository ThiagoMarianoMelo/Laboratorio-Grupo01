namespace Sprint4.Controllers.ProfessorController;

using Microsoft.AspNetCore.Mvc;
using Sprint4.Services.Professor.AdcionarSaldoAluno;
using Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;
using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;

[ApiController]
public class ProfessorController : ControllerBase
{
    IAdcionarSaldoAluno adcionandoSaldo;

    public ProfessorController(){

        adcionandoSaldo = new AdcionarSaldo();
    }

[HttpPut]
[Route("AdcionarSaldo")]
public void addSaldoAluno( [FromBody] AdcionarSaldoAlunoModel modelAdcionarSaldo ) => adcionandoSaldo.adcionarSaldoAluno(modelAdcionarSaldo);

}
