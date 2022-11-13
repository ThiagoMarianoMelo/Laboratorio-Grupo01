using Sprint4.Models.Usuario.CadastrarUsuarioModel;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;

namespace Sprint4.Services.Usuario.Interfaces.ICadastrarUsuarioServico;

public interface ICadastrarUsuarioServico {
    public UsuarioLoginRetornoModel CadastrarUsuario(CadastrarUsuarioModel usuario);
}