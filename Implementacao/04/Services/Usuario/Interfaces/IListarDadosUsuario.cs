namespace Sprint4.Services.Usuario.Interfaces.IListarDadosUsuario;

using Sprint4.Models.Usuario.UsuarioModel;

public interface IListarDadosUsuario{

    public UsuarioModel ListarDadosUser(String cpfUsuario);
}