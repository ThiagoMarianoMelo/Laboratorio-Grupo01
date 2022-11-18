using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;

public class ObterAlunosServico : IObterAlunosServico
{
    public List<UsuarioLoginRetornoModel> ObterUsuarios()
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"perfilid\" = 2", conn);
        var reader = cmd.ExecuteReader();
        List<UsuarioLoginRetornoModel> alunos = new List<UsuarioLoginRetornoModel>();
        
        while(reader.Read()) {
            var usuario = new UsuarioLoginRetornoModel();

            usuario.idUsuario    = (int)reader["idusuario"];
            usuario.nome  = reader["nome"].ToString();
            usuario.cpf = reader["cpf"].ToString();
            usuario.saldo = (int)reader["saldo"];
            usuario.senha = reader["senha"].ToString();
            usuario.Email = reader["email"].ToString();

            alunos.Add(usuario);
        }

        return alunos;
    }
}