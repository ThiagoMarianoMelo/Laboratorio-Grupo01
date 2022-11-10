namespace Sprint4.Controllers.ProfessorController;

using Microsoft.AspNetCore.Mvc;

using Sprint4.Services.Usuario.Interfaces.IListarDadosUsuario;
using Sprint4.Services.Usuario.ListarDadosUsuario;
using Sprint4.Models.Usuario.UsuarioModel;

[ApiController]
public class UsuarioController : ControllerBase 
{
    IListarDadosUsuario listaDados;

    public UsuarioController(){

        listaDados = new ListarDadosUsuario();
    }

[HttpGet]
[Route("ListarDadosUsuario")]
public UsuarioModel addSaldoAluno( [FromQuery] String cpfUsuario ) => listaDados.ListarDadosUser(cpfUsuario);

}
