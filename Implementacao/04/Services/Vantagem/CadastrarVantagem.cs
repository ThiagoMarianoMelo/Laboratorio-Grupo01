namespace Sprint4.Services.Vantagem.CadastrarVantagem;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Vantagem.CadastrarVantagemModel;
using Sprint4.Services.Vantagem.Interface.ICadastrarVantagem;

public class CadastrarVantagem : ICadastrarVantagem
{
    public void addVantagem(CadastrarVantagemModel vantagem)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("INSERT INTO public.\"vantagem\"(\"idempresa\",\"descricao\",\"urlfoto\",\"preco\") VALUES (@IDEmpresa,@Descricao,@UrlFoto,@Preco)", conn);

        cmd.Parameters.AddWithValue("IDEmpresa", vantagem.idEmpresa);
        cmd.Parameters.AddWithValue("Descricao", vantagem.descricao);
        cmd.Parameters.AddWithValue("UrlFoto", vantagem.descricao);
        cmd.Parameters.AddWithValue("Preco", vantagem.preco);

        var reader = cmd.ExecuteReader();

        reader.Read();

        conn.Close();
    }
}
