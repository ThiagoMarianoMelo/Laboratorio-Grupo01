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

            descontarSaldoContaProfessor(adcionarSaldoAlunoModel.valorQueSeraAdcionado, adcionarSaldoAlunoModel.idprofessorFromRequest);

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @idaluno", conn);

            cmdSelect.Parameters.AddWithValue("idaluno", adcionarSaldoAlunoModel.idaluno);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"usuario\" SET \"saldo\" = @Saldo WHERE \"idusuario\" = @idaluno", connUpdate);


            cmd.Parameters.AddWithValue("idaluno", adcionarSaldoAlunoModel.idaluno);
            cmd.Parameters.AddWithValue("Saldo", saldoAtual + adcionarSaldoAlunoModel.valorQueSeraAdcionado);

            var reader2 = cmd.ExecuteReader();

            reader2.Read();

            conn.Close();
            connUpdate.Close();


    }


    public void descontarSaldoContaProfessor(int saldoGasto, int idProfessor){
        
            var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @idProfessor", conn);

            cmdSelect.Parameters.AddWithValue("idProfessor", idProfessor);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"usuario\" SET \"saldo\" = @Saldo WHERE \"idusuario\" = @idProfessor", connUpdate);

            if(saldoAtual - saldoGasto < 0){
            
                throw new Exception("O saldo do professor é inferior ao escolhido");

            }else{

                 cmd.Parameters.AddWithValue("idProfessor", idProfessor);
                 cmd.Parameters.AddWithValue("Saldo", saldoAtual - saldoGasto);

                var reader2 = cmd.ExecuteReader();

                reader2.Read();
            }

            conn.Close();
            connUpdate.Close();
    }

}
   
