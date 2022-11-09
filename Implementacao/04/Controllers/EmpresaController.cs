namespace Sprint4.Controllers.EmpresaController;

using Microsoft.AspNetCore.Mvc;

using Sprint4.Models.Empresa.CadastrarEmpresaModel;
using Sprint4.Models.Usuario.UsuarioModel;
using Sprint4.Services.Empresa.CadastrarEmpresa;
using Sprint4.Services.Empresa.Interface.ICadastrarEmpresa;

[ApiController]
public class EmpresaController : ControllerBase 
{
    ICadastrarEmpresa cadastrarEmpresa;

    public EmpresaController(){

        cadastrarEmpresa = new CadastrarEmpresa();
    }

[HttpPost]
[Route("CadastrarEmpresa")]
public void addEmpresa( [FromBody] CadastrarEmpresaModel empresa ) => cadastrarEmpresa.addEmpresa(empresa);

}
