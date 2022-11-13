namespace Sprint4.Controllers.VatagemController;

using Microsoft.AspNetCore.Mvc;

using Sprint4.Models.Vantagem.CadastrarVantagemModel;
using Sprint4.Services.Vantagem.Interface.ICadastrarVantagem;
using Sprint4.Services.Vantagem.CadastrarVantagem;
using Sprint4.Services.Vantagem.Interface.IListarVantagns;
using Sprint4.Services.Vantagem.ListarVantagens;
using Sprint4.Models.Vantagem.ListarVantagemModel;

[ApiController]
public class VatagemController : ControllerBase 
{
    ICadastrarVantagem cadastrarVantagem;
    IListarVantagns listarVantagens;

    public VatagemController(){

        cadastrarVantagem = new CadastrarVantagem();
        listarVantagens    = new ListarVantagens();
    }

[HttpPost]
[Route("CadastrarVantagem")]
public void addEmpresa( [FromBody] CadastrarVantagemModel vantagem ) => cadastrarVantagem.addVantagem(vantagem);

[HttpGet]
[Route("listar-vantagens")]
public List<ListarVantagemModel> listarTodasAsVantagens() => listarVantagens.listarVantagens();

}
