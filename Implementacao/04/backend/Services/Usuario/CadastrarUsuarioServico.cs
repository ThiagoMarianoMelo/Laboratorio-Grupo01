namespace Sprint4.Services.Usuario.LogarUsuario;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Usuario.CadastrarUsuarioModel;
using Sprint4.Models.Usuario.UsuarioLoginModel;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;
using Sprint4.Services.Interfaces.Usuario.ILogarUsuario;
using Sprint4.Services.Usuario.Interfaces.ICadastrarUsuarioServico;

public class CadastrarUsuarioServico : ICadastrarUsuarioServico {
    public UsuarioLoginRetornoModel CadastrarUsuario(CadastrarUsuarioModel usuario)
    {
        var conn = new DataBaseConnection().dataBaseConnection();
        conn.Open();

        var cmd = new NpgsqlCommand("INSERT INTO public.\"usuario\"(\"nome\",\"cpf\",\"senha\",\"saldo\",\"perfilid\") VALUES (@Nome, @Cpf, @Senha, 0, @PerfilId) RETURNING idusuario", conn);

        cmd.Parameters.AddWithValue("Nome", usuario.Nome);
        cmd.Parameters.AddWithValue("Cpf", usuario.Cpf);
        cmd.Parameters.AddWithValue("Saldo", 0);
        cmd.Parameters.AddWithValue("PerfilId", usuario.PerfilId);
        cmd.Parameters.AddWithValue("Senha", usuario.Senha);

        var reader = cmd.ExecuteReader();

        UsuarioLoginRetornoModel user = new UsuarioLoginRetornoModel();

        if(reader.HasRows){
            reader.Read();

            user.idUsuario = (int)reader["idusuario"];
            user.nome  = usuario.Nome;
            user.cpf = usuario.Cpf;
            user.saldo = 0;
            user.senha = usuario.Senha;
            user.perfil = "Empresa";
            conn.Close();
            return user;
        }

        return null;
    }
}