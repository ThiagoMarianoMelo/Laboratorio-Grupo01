namespace Sprint4.Services.Interfaces.Usuario.ILogarUsuario;

using Sprint4.Models.Usuario.UsuarioLoginModel;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;

public interface ILogarUsuario{

    public UsuarioLoginRetornoModel Login(UsuarioLoginModel user);
}