namespace Sprint4.Services.Vantagem.ListarVantagens;

using System.Collections.Generic;
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
            vantagem.nome = reader["nome"].ToString();

            int empresaId = (int)reader["idempresa"];

            var connectionEmpresa = new DataBaseConnection().dataBaseConnection();
            connectionEmpresa.Open();
            
            var cmdEmpresa = new NpgsqlCommand("SELECT * FROM public.\"usuario\" WHERE \"idusuario\" = @EmpresaId", connectionEmpresa);
            cmdEmpresa.Parameters.AddWithValue("EmpresaId",empresaId );
            var readerPerfil = cmdEmpresa.ExecuteReader();

            if(readerPerfil.HasRows) {
                readerPerfil.Read();

                vantagem.Empresa = readerPerfil["nome"].ToString();
            }
            
            connectionEmpresa.Close();
            listaDeVantagens.Add(vantagem);
        }

        conn.Close();

        return listaDeVantagens;
    }
}

