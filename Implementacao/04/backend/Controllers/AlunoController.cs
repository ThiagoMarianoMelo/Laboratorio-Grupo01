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
using Sprint4.Services.Email.EmailService;
using Sprint4.Connection.DataBaseConnection;
using Npgsql;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;

[ApiController]
public class AlunoController : ControllerBase 
{
    public IEcontrarVantagem encontrarVantagemEscolhida;
    public ICadastrarTransacao cadastrarTransacao;
    public IDescontarSaldo descontarSaldo;
    public IObterAlunosServico obterAlunosServico;


    public AlunoController(){
        encontrarVantagemEscolhida = new EncontrarVantagem();
        cadastrarTransacao = new CadastrarTransacao();
        descontarSaldo = new DescontarSaldo();
        obterAlunosServico = new ObterAlunosServico();
    }

    [HttpGet]
    [Route("listar-alunos")]
    public List<UsuarioLoginRetornoModel> ObterAlunos() => obterAlunosServico.ObterUsuarios();

[HttpPost]
[Route("comprar-vantagem")]
    public int adquirirVantagem( [FromBody] ComprarVantagemModel modelAdquiriVantagemModel){

        ListarVantagemModel vantagemEscolhida = encontrarVantagemEscolhida.getVantagem(modelAdquiriVantagemModel.idVantagem);
        descontarSaldo.descontarSaldoConta(vantagemEscolhida.preco, modelAdquiriVantagemModel.idAluno);

        TrasacaoModel transacaoGerada = new TrasacaoModel();

        var conn = new DataBaseConnection().dataBaseConnection();
        conn.Open();
        var cmd = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @idUsuario ", conn);
        
        cmd.Parameters.AddWithValue("idUsuario", modelAdquiriVantagemModel.idAluno);
        var reader = cmd.ExecuteReader();

        reader.Read();

        var emailAluno = reader["email"].ToString();
        var nomeAluno = reader["nome"].ToString();
        conn.Close();

        transacaoGerada.anotacao = "compra de: " + vantagemEscolhida.descricao;
        transacaoGerada.idusuario = modelAdquiriVantagemModel.idAluno;
        transacaoGerada.IdBeneficiario = vantagemEscolhida.idEmpresa;
        transacaoGerada.dataTransacao = DateTime.Now;
        transacaoGerada.preco = vantagemEscolhida.preco;

        Guid codigoVantagem = Guid.NewGuid();
    
        String bodyAluno = "Aqui está o seu cupom para você utilizar sua vantagem: " + vantagemEscolhida.descricao + "\n\n" + codigoVantagem;

        EmailService sender = new EmailService();

        sender.sendEmail(emailAluno,"Sua troca ocorreu com sucesso! Utilize já o seu cupom", bodyAluno);

        String bodyEmpresa = "O aluno " + nomeAluno + " acabou de resgatar a vantagem "+ vantagemEscolhida.descricao + ". O código para a utilização do cupom é:\n " + codigoVantagem;

        sender.sendEmail(vantagemEscolhida.Empresa,"Um aluno acabou de resgatar uma vantagem. Veja agora!", bodyEmpresa);


        return cadastrarTransacao.cadastrarTransacao(transacaoGerada);

    }

}
