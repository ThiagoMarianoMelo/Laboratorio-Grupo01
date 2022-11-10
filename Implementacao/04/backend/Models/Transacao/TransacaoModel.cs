namespace Sprint4.Models.Transacao.TrasacaoModel;

public class TrasacaoModel{


    public int idusuario {get; set; }

    public DateTime dataTransacao {get; set; }

    public String anotacao {get; set; }

    public int preco {get; set; }

    public int IdBeneficiario {get; set;}
}