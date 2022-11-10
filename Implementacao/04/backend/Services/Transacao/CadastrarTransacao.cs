namespace Sprint4.Services.Transacao.CadastrarTransacao;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Transacao.TrasacaoModel;
using Sprint4.Services.Transacao.Interfaces.ICadastrarTransacao;

public class CadastrarTransacao : ICadastrarTransacao
{
    public void cadastrarTransacao(TrasacaoModel transacao)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("INSERT INTO public.\"historico\"(\"idusuario\",\"datatransacao\",\"anotacao\",\"preco\") VALUES (@IDUser,@Data,@Anotacao,@Preco)", conn);

        cmd.Parameters.AddWithValue("IDUser", transacao.idusuario);
        cmd.Parameters.AddWithValue("Data", transacao.dataTransacao);
        cmd.Parameters.AddWithValue("Anotacao", transacao.anotacao);
        cmd.Parameters.AddWithValue("Preco", transacao.preco);

        var reader = cmd.ExecuteReader();

        reader.Read();

        conn.Close();
    }
}