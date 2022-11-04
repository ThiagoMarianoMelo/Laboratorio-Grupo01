namespace Sprint4.Services.Usuario.ListarDadosUsuario;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Usuario.UsuarioModel;
using Sprint4.Services.Usuario.Interfaces.IListarDadosUsuario;

public class ListarDadosUsuario : IListarDadosUsuario
{
    public UsuarioModel listarDadosUser(string cpfUsuario)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        UsuarioModel usuarioEncontrado = new UsuarioModel();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"cpf\" = @CPFusuario ", conn);

        cmd.Parameters.AddWithValue("CPFusuario", cpfUsuario);

        var reader = cmd.ExecuteReader();

        reader.Read();

        usuarioEncontrado.idUsuario  = (int)reader["idusuario"];
        usuarioEncontrado.cpf        = reader["cpf"].ToString();
        usuarioEncontrado.saldo      = (int)reader["saldo"];

        conn.Close();

        return usuarioEncontrado;
    }
}