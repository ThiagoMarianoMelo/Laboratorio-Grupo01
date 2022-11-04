namespace Sprint4.Services.Transacao.ListarTransacoes;

using System.Collections.Generic;
using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Transacao.listarTransacaoModel;
using Sprint4.Services.Transacao.Interfaces.IListarTransacoes;
public class ListarTransacoes : IListarTransacoes
{
    public List<listarTransacaoModel> listarHistorico(int idUsuario)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"historico\" WHERE \"idusuario\" = @IDUsuario", conn);

        cmd.Parameters.AddWithValue("IDUsuario", idUsuario);

        var reader = cmd.ExecuteReader();

        List<listarTransacaoModel> transacoes = new List<listarTransacaoModel>();

        while(reader.Read()){

            listarTransacaoModel transacaoEncontrada = new listarTransacaoModel();

            transacaoEncontrada.idTransacao = (int)reader["idtransacao"];
            transacaoEncontrada.idUsuario = (int)reader["idusuario"];
            transacaoEncontrada.dataTransacao = (DateTime)reader["datatransacao"];
            transacaoEncontrada.anotacao = reader["anotacao"].ToString();
            transacaoEncontrada.preco = (int)reader["preco"];

            transacoes.Add(transacaoEncontrada);  

        }

        conn.Close();

        return transacoes;
    }
}