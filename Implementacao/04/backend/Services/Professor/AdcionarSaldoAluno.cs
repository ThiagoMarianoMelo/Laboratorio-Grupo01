namespace Sprint4.Services.Professor.AdcionarSaldoAluno;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;
using Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;
using Sprint4.Models.Transacao.TrasacaoModel;
using Sprint4.Services.Email.EmailService;

public class AdcionarSaldo : IAdcionarSaldoAluno
{
    public  TrasacaoModel adcionarSaldoAluno(AdcionarSaldoAlunoModel adcionarSaldoAlunoModel)
    {
            var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"cpf\" = @cpfaluno", conn);

            cmdSelect.Parameters.AddWithValue("cpfaluno", adcionarSaldoAlunoModel.cpfAluno);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];
            int idAluno = (int)reader["idusuario"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"usuario\" SET \"saldo\" = @Saldo WHERE \"cpf\" = @cpfAluno", connUpdate);


            cmd.Parameters.AddWithValue("cpfAluno", adcionarSaldoAlunoModel.cpfAluno);
            cmd.Parameters.AddWithValue("Saldo", saldoAtual + adcionarSaldoAlunoModel.valorQueSeraAdcionado);

            var readerUpdate = cmd.ExecuteReader();

            enviarEmailAluno(idAluno,adcionarSaldoAlunoModel.valorQueSeraAdcionado);

            conn.Close();
            connUpdate.Close();


            TrasacaoModel transacaoGerada = new TrasacaoModel();

            transacaoGerada.idusuario = adcionarSaldoAlunoModel.idprofessorFromRequest;
            transacaoGerada.dataTransacao = DateTime.Now;
            transacaoGerada.preco = adcionarSaldoAlunoModel.valorQueSeraAdcionado;
            transacaoGerada.anotacao = adcionarSaldoAlunoModel.anotacao;
            transacaoGerada.IdBeneficiario = idAluno;

            return transacaoGerada;
    }

    public void enviarEmailAluno(int idAluno, int valorAdcionado){

            var conn = new DataBaseConnection().dataBaseConnection();

            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"aluno\" WHERE \"idaluno\" = @IDaluno", conn);

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
   
