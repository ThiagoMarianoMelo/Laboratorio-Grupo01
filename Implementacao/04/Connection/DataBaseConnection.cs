namespace Sprint4.Connection.DataBaseConnection;
using Npgsql;

public class DataBaseConnection{

    public NpgsqlConnection dataBaseConnection() => new NpgsqlConnection("Host=ec2-54-163-34-107.compute-1.amazonaws.com;Username=kzrolhlsznxlko;Password=71cc3c45b803fb87cb2e680e2b1fe39d4a061db64795fe368aff062f591314bf;Database=deg8nlq06thguq");

}


