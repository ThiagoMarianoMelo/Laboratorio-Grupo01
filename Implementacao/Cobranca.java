
public class Cobranca {
    private double valorTotal;
    private Aluno aluno;
    private boolean foiPaga;

    public void cobrarFatura(){
        this.valorTotal = Aluno.getMatriculas().stream().map(e -> e.getDisciplina()).map(e -> e.getPreco()).sum().mapToDouble();
    }
}
