using System;

namespace Sprint4.Models.Usuario.UsuarioLoginRetornoModel;


public class UsuarioLoginRetornoModel{

    public int idUsuario {get; set; }
    public String cpf {get; set; }
    public String senha {get; set; }
    public int saldo {get; set; }
    public String nome {get; set; }
    public String perfil {get; set; }
}