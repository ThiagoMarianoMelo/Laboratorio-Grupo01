namespace Sprint4.Services.Empresa.CadastrarEmpresa;

using Npgsql;
using Sprint4.Connection.DataBaseConnection;
using Sprint4.Models.Empresa.CadastrarEmpresaModel;
using Sprint4.Services.Empresa.Interface.ICadastrarEmpresa;

public class CadastrarEmpresa : ICadastrarEmpresa
{
    public void addEmpresa(CadastrarEmpresaModel empresa)
    {
        var conn = new DataBaseConnection().dataBaseConnection();

        conn.Open();

        var cmd = new NpgsqlCommand("INSERT INTO public.\"empresa\"(\"nome\",\"datadecadastro\") VALUES (@Nome,@DataDeCadastro)", conn);

        cmd.Parameters.AddWithValue("Nome", empresa.nome);
        cmd.Parameters.AddWithValue("DataDeCadastro", DateTime.Now);
        var reader = cmd.ExecuteReader();

        reader.Read();

        conn.Close();
    }
}