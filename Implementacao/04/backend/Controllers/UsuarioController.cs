namespace Sprint4.Controllers.ProfessorController;

using Microsoft.AspNetCore.Mvc;

using Sprint4.Services.Usuario.Interfaces.IListarDadosUsuario;
using Sprint4.Services.Usuario.ListarDadosUsuario;
using Sprint4.Models.Usuario.UsuarioModel;
using Sprint4.Services.Interfaces.Usuario.ILogarUsuario;
using Sprint4.Services.Usuario.LogarUsuario;
using Sprint4.Models.Usuario.UsuarioLoginModel;
using Sprint4.Models.Usuario.UsuarioLoginRetornoModel;

[ApiController]
public class UsuarioController : ControllerBase 
{
    IListarDadosUsuario listaDados;
    ILogarUsuario logarUsuario;

    public UsuarioController(){

        listaDados = new ListarDadosUsuario();
        logarUsuario = new LogarUsuario();
    }

[HttpGet]
[Route("ListarDadosUsuario")]
public UsuarioModel addSaldoAluno( [FromQuery] String cpfUsuario ) => listaDados.ListarDadosUser(cpfUsuario);


[HttpPost]
[Route("Login")]
public  UsuarioLoginRetornoModel logarUser( [FromQuery] UsuarioLoginModel user ) => logarUsuario.Login(user);

}
