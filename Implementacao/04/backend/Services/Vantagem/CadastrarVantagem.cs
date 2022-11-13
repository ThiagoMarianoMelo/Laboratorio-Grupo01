namespace Sprint4.Services.Vantagem.CadastrarVantagem;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Vantagem.CadastrarVantagemModel;
using Sprint4.Services.Vantagem.Interface.ICadastrarVantagem;

public class CadastrarVantagem : ICadastrarVantagem
{
    public int addVantagem(CadastrarVantagemModel vantagem)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("INSERT INTO public.\"vantagem\"(\"idempresa\",\"descricao\",\"urlfoto\",\"preco\",\"nome\") VALUES (@IDEmpresa,@Descricao,@UrlFoto,@Preco,@Nome) RETURNING idvantagem", conn);

        cmd.Parameters.AddWithValue("IDEmpresa", vantagem.idEmpresa);
        cmd.Parameters.AddWithValue("Descricao", vantagem.descricao);
        cmd.Parameters.AddWithValue("UrlFoto", vantagem.urlFoto);
        cmd.Parameters.AddWithValue("Preco", vantagem.preco);
        cmd.Parameters.AddWithValue("Nome", vantagem.Nome);

        var reader = cmd.ExecuteReader();

        reader.Read();
        if (reader.HasRows) {
            int idVantagem = (int)reader["idvantagem"];
            conn.Close();

            return idVantagem;
        }

        return 0;
    }
}
