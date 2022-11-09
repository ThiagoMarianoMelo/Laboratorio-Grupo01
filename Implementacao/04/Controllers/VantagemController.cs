namespace Sprint4.Controllers.VatagemController;

using Microsoft.AspNetCore.Mvc;

using Sprint4.Models.Vantagem.CadastrarVantagemModel;
using Sprint4.Services.Vantagem.Interface.ICadastrarVantagem;
using Sprint4.Services.Vantagem.CadastrarVantagem;

[ApiController]
public class VatagemController : ControllerBase 
{
    ICadastrarVantagem cadastrarVantagem;

    public VatagemController(){

        cadastrarVantagem = new CadastrarVantagem();
    }

[HttpPost]
[Route("CadastrarVantagem")]
public void addEmpresa( [FromBody] CadastrarVantagemModel vantagem ) => cadastrarVantagem.addVantagem(vantagem);

}
