namespace Sprint4.Services.Transacao.ListarTransacoes;

using System.Collections.Generic;
using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Transacao.listarTransacaoModel;
using Sprint4.Services.Transacao.Interfaces.IListarTransacoes;
using Sprint4.Models.Transacao.TrasacaoModel;
using System;

public class ListarTransacoes : IListarTransacoes
{
    public listarTransacaoModel listarHistorico(int idUsuario)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("SELECT * FROM public.\"historico\" WHERE \"idusuario\" = @IDUsuario", conn);

        cmd.Parameters.AddWithValue("IDUsuario", idUsuario);

        var reader = cmd.ExecuteReader();

        List<TrasacaoModel> transacoes = new List<TrasacaoModel>();

        while(reader.Read()&& reader.HasRows){

            TrasacaoModel transacaoEncontrada = new TrasacaoModel();

            transacaoEncontrada.idtransacao = (int)reader["idtransacao"];
            transacaoEncontrada.idusuario = (int)reader["idusuario"];
            transacaoEncontrada.dataTransacao = (DateTime)reader["datatransacao"];
            transacaoEncontrada.anotacao = reader["anotacao"].ToString();
            transacaoEncontrada.preco = (int)reader["preco"];
            transacaoEncontrada.IdBeneficiario = (int)reader["idbeneficiario"];


            transacoes.Add(transacaoEncontrada);

        }

            var connbenef = new DataBaseConnection().dataBaseConnection();

            connbenef.Open();

            var cmdbenef = new NpgsqlCommand("SELECT * FROM public.\"historico\" WHERE \"idbeneficiario\" = @IDusuario", connbenef);

            cmdbenef.Parameters.AddWithValue("IDusuario", idUsuario);

            var readerbenef = cmdbenef.ExecuteReader();

            while(readerbenef.Read() && readerbenef.HasRows){

                    TrasacaoModel transacaoEncontrada = new TrasacaoModel();

                    transacaoEncontrada.idtransacao = (int)readerbenef["idtransacao"];
                    transacaoEncontrada.idusuario = (int)readerbenef["idusuario"];
                    transacaoEncontrada.dataTransacao = (DateTime)readerbenef["datatransacao"];
                    transacaoEncontrada.anotacao = readerbenef["anotacao"].ToString();
                    transacaoEncontrada.preco = (int)readerbenef["preco"];
                    transacaoEncontrada.IdBeneficiario = (int)readerbenef["idbeneficiario"];

                    listarTransacaoModel transacaoRetorno = new listarTransacaoModel();

                    transacoes.Add(transacaoEncontrada);

                }
                
        listarTransacaoModel retorno = new listarTransacaoModel();

        var connUser = new DataBaseConnection().dataBaseConnection();

        connUser.Open();

        var cmdUser = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @IDUsuario", connUser);

        cmdUser.Parameters.AddWithValue("IDUser", idUsuario);

        var readerUser = cmdUser.ExecuteReader();

        readerUser.Read();

        retorno.saldoAtualUser = (int)readerUser["saldo"];
        retorno.transacoes = transacoes;

        connbenef.Close();

        conn.Close();

        connUser.Close();

        return retorno;
    }

}
