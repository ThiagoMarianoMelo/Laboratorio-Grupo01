namespace Sprint4.Services.Usuario.DescontarSaldo;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Services.Usuario.Interfaces.IDescontarSaldo;

public class DescontarSaldo : IDescontarSaldo
{
    public void descontarSaldoConta(int saldoGasto, int idUsuario)
    {
        var conn = new DataBaseConnection().dataBaseConnection();
            conn.Open();

            var cmdSelect = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @idusuario", conn);

            cmdSelect.Parameters.AddWithValue("idusuario", idUsuario);

            var reader = cmdSelect.ExecuteReader();

            reader.Read();
            
            var connUpdate = new DataBaseConnection().dataBaseConnection();

            connUpdate.Open();

            int saldoAtual = (int)reader["saldo"];

            var cmd = new NpgsqlCommand("UPDATE  public.\"usuario\" SET \"saldo\" = @Saldo WHERE \"idusuario\" = @idusuario", connUpdate);

            if(saldoAtual - saldoGasto < 0){
            
                throw new Exception("Saldo insuficiente!");

            }else{

                 cmd.Parameters.AddWithValue("idusuario", idUsuario);
                 cmd.Parameters.AddWithValue("Saldo", saldoAtual - saldoGasto);

                var reader2 = cmd.ExecuteReader();

                reader2.Read();
            }

            conn.Close();
            connUpdate.Close();
    }
}