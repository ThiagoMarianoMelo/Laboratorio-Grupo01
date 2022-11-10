namespace Sprint4.Connection.DataBaseConnection;
using Npgsql;

public class DataBaseConnection{

    //uri da connection: postgres://gsfedlqdwiptko:f321ae522eb2d3f9cf5dfb3cd562778cd1c8366b0d829c29ec574d10d1f62394@ec2-23-20-140-229.compute-1.amazonaws.com:5432/ddf5podsqc197u
    //(?ssl=verify-full)
    public NpgsqlConnection dataBaseConnection() => new NpgsqlConnection("Host=ec2-23-20-140-229.compute-1.amazonaws.com;Username=gsfedlqdwiptko;Password=f321ae522eb2d3f9cf5dfb3cd562778cd1c8366b0d829c29ec574d10d1f62394;Database=ddf5podsqc197u");

}


