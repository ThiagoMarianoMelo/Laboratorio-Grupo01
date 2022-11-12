namespace Sprint4.Services.Vantagem.EncontrarVantagem;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Vantagem.ListarVantagemModel;
using Sprint4.Services.Vantagem.Interface.IEcontrarVantagem;

public class EncontrarVantagem: IEcontrarVantagem {

    public ListarVantagemModel getVantagem(int idVantagem){

        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"vantagem\" WHERE \"idvantagem\" = @IDVantagem", conn);
        
        cmd.Parameters.AddWithValue("IDVantagem", idVantagem);

        var reader = cmd.ExecuteReader();
        
        reader.Read();

        ListarVantagemModel vantagem = new ListarVantagemModel();

        vantagem.descricao  = reader["descricao"].ToString();
        vantagem.preco =  (int)reader["preco"];
        vantagem.urlFoto = reader["urlfoto"].ToString();
        vantagem.idEmpresa = (int)reader["idempresa"];

        conn.Close();

        return vantagem;
    }
}