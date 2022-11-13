namespace Sprint4.Services.Usuario.LogarUsuario;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Usuario.UsuarioLoginModel;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;
using Sprint4.Services.Interfaces.Usuario.ILogarUsuario;



public class LogarUsuario : ILogarUsuario{

    public UsuarioLoginRetornoModel Login(UsuarioLoginModel usuario){
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"cpf\" = @Cpf AND \"senha\" = @Senha", conn);

        cmd.Parameters.AddWithValue("cpf", usuario.cpf);
        cmd.Parameters.AddWithValue("Senha", usuario.senha);

        var reader = cmd.ExecuteReader();

        UsuarioLoginRetornoModel user = new UsuarioLoginRetornoModel();

        if(reader.HasRows){
            reader.Read();

            user.idUsuario    = (int)reader["idusuario"];
            user.nome  = reader["nome"].ToString();
            user.cpf = reader["cpf"].ToString();
            user.saldo = (int)reader["saldo"];
            user.senha = reader["senha"].ToString();
            user.isProfessor = (bool) reader["isprofessor"];

            conn.Close();
            return user;
        }

        conn.Close();
        return null;
    }
}