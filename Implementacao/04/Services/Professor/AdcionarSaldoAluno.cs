namespace Sprint4.Services.Professor.AdcionarSaldoAluno;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Professor.AdcionarSaldoAlunoModel;
using Sprint4.Services.Professor.Interfaces.IAdcionarSaldoAluno;

public class AdcionarSaldo : IAdcionarSaldoAluno
{
    public void adcionarSaldoAluno(AdcionarSaldoAlunoModel adcionarSaldoAlunoModel)
    {
            var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            descontarSaldoContaProfessor(adcionarSaldoAlunoModel.valorQueSeraAdcionado, adcionarSaldoAlunoModel.professorFromRequest);

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"aluno\" WHERE \"cpf\" = @cpfAluno", conn);

            cmdSelect.Parameters.AddWithValue("cpfAluno", adcionarSaldoAlunoModel.cpfAluno);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"aluno\" SET \"saldo\" = @Saldo WHERE \"cpf\" = @cpfAluno", connUpdate);


            cmd.Parameters.AddWithValue("cpfAluno", adcionarSaldoAlunoModel.cpfAluno);
            cmd.Parameters.AddWithValue("Saldo", saldoAtual + adcionarSaldoAlunoModel.valorQueSeraAdcionado);

            var reader2 = cmd.ExecuteReader();

            reader2.Read();

            conn.Close();
            connUpdate.Close();


    }


    public void descontarSaldoContaProfessor(int saldoGasto, String cpfProfessor){
        
            var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"professor\" WHERE \"cpf\" = @cpfProfessor", conn);

            cmdSelect.Parameters.AddWithValue("cpfProfessor", cpfProfessor);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"professor\" SET \"saldo\" = @Saldo WHERE \"cpf\" = @cpfProfessor", connUpdate);

            if(saldoAtual - saldoGasto < 0){
            
                throw new Exception("O saldo do professor é inferior ao escolhido");

            }else{

                 cmd.Parameters.AddWithValue("cpfProfessor", cpfProfessor);
                 cmd.Parameters.AddWithValue("Saldo", saldoAtual - saldoGasto);

                var reader2 = cmd.ExecuteReader();

                reader2.Read();
            }

            conn.Close();
            connUpdate.Close();
    }

}
   
