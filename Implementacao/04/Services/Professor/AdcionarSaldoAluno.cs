namespace Sprint4.Services.Professor.AdcionarSaldoAluno;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;
using Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;
using Sprint4.Services.Transacao.CadastrarTransacao;
using Sprint4.Models.Transacao.TrasacaoModel;

public class AdcionarSaldo : IAdcionarSaldoAluno
{
    public  TrasacaoModel adcionarSaldoAluno(AdcionarSaldoAlunoModel adcionarSaldoAlunoModel)
    {
            var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"cpf\" = @cpfAluno", conn);

            cmdSelect.Parameters.AddWithValue("cpfAluno", adcionarSaldoAlunoModel.cpfAluno);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"usuario\" SET \"saldo\" = @Saldo WHERE \"cpf\" = @cpfAluno", connUpdate);


            cmd.Parameters.AddWithValue("cpfAluno", adcionarSaldoAlunoModel.cpfAluno);
            cmd.Parameters.AddWithValue("Saldo", saldoAtual + adcionarSaldoAlunoModel.valorQueSeraAdcionado);

            var reader2 = cmd.ExecuteReader();

            reader2.Read();

            conn.Close();
            connUpdate.Close();


            TrasacaoModel transacaoGerada = new TrasacaoModel();

            transacaoGerada.idusuario = adcionarSaldoAlunoModel.idprofessorFromRequest;
            transacaoGerada.dataTransacao = DateTime.Now;
            transacaoGerada.preco = adcionarSaldoAlunoModel.valorQueSeraAdcionado;
            transacaoGerada.anotacao = adcionarSaldoAlunoModel.anotacao;

            return transacaoGerada;
    }

}
   
