using Microsoft.AspNetCore.Mvc;

namespace Sprint4.Controllers.AlunoController;
using Sprint4.Models.Aluno.ComprarVantagemModel;
using Sprint4.Models.Transacao.TrasacaoModel;
using Sprint4.Models.Vantagem.ListarVantagemModel;
using Sprint4.Services.Usuario.DescontarSaldo;
using Sprint4.Services.Vantagem.Interface.IEcontrarVantagem;
using Sprint4.Services.Transacao.CadastrarTransacao;
using Sprint4.Services.Vantagem.EncontrarVantagem;
using Sprint4.Services.Usuario.Interfaces.IDescontarSaldo;
using Sprint4.Services.Transacao.Interfaces.ICadastrarTransacao;
using Sprint4.Services.Usuario.DescontarSaldo;
using System;

[ApiController]
public class AlunoController : ControllerBase 
{
    public IEcontrarVantagem encontrarVantagemEscolhida;
    public ICadastrarTransacao cadastrarTransacao;
    public IDescontarSaldo descontarSaldo;

    public AlunoController(){
        encontrarVantagemEscolhida = new EncontrarVantagem();
        cadastrarTransacao = new CadastrarTransacao();
        descontarSaldo = new DescontarSaldo();
    }

[HttpPost]
[Route("comprarVantagem")]
    public void adquirirVantagem( [FromBody] ComprarVantagemModel modelAdquiriVantagemModel){

        ListarVantagemModel vantagemEscolhida = encontrarVantagemEscolhida.getVantagem(modelAdquiriVantagemModel.idVantagem);

        descontarSaldo.descontarSaldoConta(vantagemEscolhida.preco, modelAdquiriVantagemModel.idAluno);

        TrasacaoModel transacaoGerada = new TrasacaoModel();

        transacaoGerada.anotacao = "compra de: " + vantagemEscolhida.descricao;
        transacaoGerada.idusuario = modelAdquiriVantagemModel.idAluno;
        transacaoGerada.IdBeneficiario = vantagemEscolhida.idEmpresa;
        transacaoGerada.dataTransacao = DateTime.Now;
        transacaoGerada.preco = vantagemEscolhida.preco;

        cadastrarTransacao.cadastrarTransacao(transacaoGerada);

    }

}
