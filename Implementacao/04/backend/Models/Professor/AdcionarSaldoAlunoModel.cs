using System;

namespace Sprint4.Models.Professor.AdcionarSaldoAlunoModel;

public class EnviarMoedasModel{

    public int idProfessor { get; set; }
    public int idAluno { get; set; }

    public int valorEmMoedas { get; set; }

    public String anotacao { get; set;}
}