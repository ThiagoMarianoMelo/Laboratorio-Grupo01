namespace Sprint4.Controllers.TransacaoController;

using Microsoft.AspNetCore.Mvc;
using Sprint4.Services.Transacao.Interfaces.IListarTransacoes;
using Sprint4.Services.Transacao.ListarTransacoes;
using Sprint4.Models.Transacao.listarTransacaoModel;

[ApiController]
public class TransacaoController : ControllerBase 
{
    IListarTransacoes listaTransacoes;

    public TransacaoController(){

        listaTransacoes = new ListarTransacoes();
    }

[HttpGet]
[Route("ListarTransacoesUsuario")]
public listarTransacaoModel listarTransacoes( [FromQuery] int idusuario ) => listaTransacoes.listarHistorico(idusuario);

}