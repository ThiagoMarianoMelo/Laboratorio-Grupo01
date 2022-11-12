namespace Sprint4.Services.Vantagem.ListarVantagens;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Vantagem.ListarVantagemModel;
using Sprint4.Services.Vantagem.Interface.IListarVantagns;

public class ListarVantagens : IListarVantagns{

    public List<ListarVantagemModel> listarVantagens(){

        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        List<ListarVantagemModel> listaDeVantagens = new List<ListarVantagemModel>();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"vantagem\"", conn);

        var reader = cmd.ExecuteReader();

        while(reader.Read()){

            ListarVantagemModel vantagem = new ListarVantagemModel();

            vantagem.idVantagem =  (int)reader["idvantagem"];
            vantagem.descricao  = reader["descricao"].ToString();
            vantagem.preco =  (int)reader["preco"];
            vantagem.urlFoto = reader["urlfoto"].ToString();
            vantagem.idEmpresa = (int)reader["idempresa"];

            listaDeVantagens.Add(vantagem);

        }

        conn.Close();

        return listaDeVantagens;
    }
}

