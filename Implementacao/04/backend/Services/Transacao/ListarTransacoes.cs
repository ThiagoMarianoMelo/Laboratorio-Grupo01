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


            var connUser = new DataBaseConnection().dataBaseConnection();

            connUser.Open();

            var cmdUser = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @IDUsuario", connUser);

            cmdUser.Parameters.AddWithValue("IDUser", transacaoEncontrada.idUsuario);

            var readerUser = cmdUser.ExecuteReader();

            readerUser.Read();

            transacaoEncontrada.saldoAtualUser = (int)readerUser["saldo"];

            transacoes.Add(transacaoEncontrada);

            connUser.Close();

        }

            var connbenef = new DataBaseConnection().dataBaseConnection();

            connbenef.Open();

            var cmdbenef = new NpgsqlCommand("SELECT * FROM public.\"transacao\" WHERE \"idbeneficiario\" = @IDusuario", connbenef);

            cmdbenef.Parameters.AddWithValue("IDusuario", idUsuario);

            var readerbenef = cmdbenef.ExecuteReader();

            while(readerbenef.Read()){

                bool transacaoNaoMapeada = false;

                foreach (listarTransacaoModel transacao in transacoes )
                {
                    transacaoNaoMapeada = (transacao.idTransacao == (int)reader["idtransacao"]);
                }

                if(!transacaoNaoMapeada){

                    listarTransacaoModel transacaoEncontrada = new listarTransacaoModel();

                    transacaoEncontrada.idTransacao = (int)reader["idtransacao"];
                    transacaoEncontrada.idUsuario = (int)reader["idusuario"];
                    transacaoEncontrada.dataTransacao = (DateTime)reader["datatransacao"];
                    transacaoEncontrada.anotacao = reader["anotacao"].ToString();
                    transacaoEncontrada.preco = (int)reader["preco"];

                    transacoes.Add(transacaoEncontrada);

                }

            }

            connbenef.Close();

        conn.Close();

        return transacoes;
    }
}