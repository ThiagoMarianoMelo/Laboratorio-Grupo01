namespace Sprint4.Services.Transacao.CadastrarTransacao;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Transacao.TrasacaoModel;
using Sprint4.Services.Transacao.Interfaces.ICadastrarTransacao;

public class CadastrarTransacao : ICadastrarTransacao
{
    public int cadastrarTransacao(TrasacaoModel transacao)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("INSERT INTO public.\"historico\"(\"idusuario\",\"datatransacao\",\"anotacao\",\"preco\",\"idbeneficiario\") VALUES (@IDUser,@Data,@Anotacao,@Preco,@IdBeneficiario) RETURNING idtransacao", conn);

        cmd.Parameters.AddWithValue("IDUser", transacao.idusuario);
        cmd.Parameters.AddWithValue("Data", transacao.dataTransacao);
        cmd.Parameters.AddWithValue("Anotacao", transacao.anotacao);
        cmd.Parameters.AddWithValue("Preco", transacao.preco);
        cmd.Parameters.AddWithValue("IdBeneficiario", transacao.IdBeneficiario);

        var reader = cmd.ExecuteReader();

        reader.Read();
        if (reader.HasRows) {
            int idTransacao = (int) reader["idtransacao"];
            conn.Close();
            return idTransacao;
        }

        return 0;
    }
}