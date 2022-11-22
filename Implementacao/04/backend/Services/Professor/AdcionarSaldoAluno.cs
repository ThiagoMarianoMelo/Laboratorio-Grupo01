namespace Sprint4.Services.Professor.AdcionarSaldoAluno;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;
using Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;
using Sprint4.Models.Transacao.TrasacaoModel;
using Sprint4.Services.Email.EmailService;
using System;

public class AdcionarSaldo : IAdcionarSaldoAluno
{
    public  TrasacaoModel adcionarSaldoAluno(EnviarMoedasModel adcionarSaldoAlunoModel)
    {
            var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @idUsuario", conn);

            cmdSelect.Parameters.AddWithValue("idUsuario", adcionarSaldoAlunoModel.idAluno);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];
            int idAluno = (int)reader["idusuario"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"usuario\" SET \"saldo\" = @Saldo WHERE \"idusuario\" = @idUsuario", connUpdate);


            cmd.Parameters.AddWithValue("idUsuario", adcionarSaldoAlunoModel.idAluno);
            cmd.Parameters.AddWithValue("Saldo", saldoAtual + adcionarSaldoAlunoModel.valorEmMoedas);

            var readerUpdate = cmd.ExecuteReader();

            enviarEmailAluno(adcionarSaldoAlunoModel.idAluno, adcionarSaldoAlunoModel.valorEmMoedas);

            conn.Close();
            connUpdate.Close();


            TrasacaoModel transacaoGerada = new TrasacaoModel();

            transacaoGerada.idusuario = adcionarSaldoAlunoModel.idProfessor;
            transacaoGerada.dataTransacao = DateTime.Now;
            transacaoGerada.preco = adcionarSaldoAlunoModel.valorEmMoedas;
            transacaoGerada.anotacao = adcionarSaldoAlunoModel.anotacao;
            transacaoGerada.IdBeneficiario = adcionarSaldoAlunoModel.idAluno;

            return transacaoGerada;
    }

    public void enviarEmailAluno(int idAluno, int valorAdcionado){

            var conn = new DataBaseConnection().dataBaseConnection();

            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @IDaluno", conn);

            cmdSelect.Parameters.AddWithValue("IDaluno", idAluno);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();

            String email = reader["email"].ToString();

            conn.Close();

            String body = "Foram adcionados "+valorAdcionado+" pontos ao seu perfil :)";

            EmailService sender = new EmailService();

            sender.sendEmail(email,"Você recebeu novos pontos escolares, confira!", body);

    }

}
   
