namespace Sprint4.Controllers.VatagemController;

using Microsoft.AspNetCore.Mvc;

using Sprint4.Models.Vantagem.CadastrarVantagemModel;
using Sprint4.Services.Vantagem.Interface.ICadastrarVantagem;
using Sprint4.Services.Vantagem.CadastrarVantagem;
using Sprint4.Services.Vantagem.Interface.IListarVantagns;
using Sprint4.Services.Vantagem.ListarVantagens;
using Sprint4.Models.Vantagem.ListarVantagemModel;
using System.Collections.Generic;

[ApiController]
public class VantagemController : ControllerBase 
{
    ICadastrarVantagem cadastrarVantagem;
    IListarVantagns listarVantagns;

    public VantagemController(){

        cadastrarVantagem = new CadastrarVantagem();
        listarVantagns    = new ListarVantagens();
    }

[HttpPost]
[Route("cadastrar-vantagem")]
public int addEmpresa( [FromBody] CadastrarVantagemModel vantagem ) => cadastrarVantagem.addVantagem(vantagem);

[HttpGet]
[Route("ListarVantagens")]
public List<ListarVantagemModel> listVatangens() => listarVantagns.listarVantagens();

}
