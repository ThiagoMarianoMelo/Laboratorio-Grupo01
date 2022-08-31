public class Cobranca {
    private double valorTotal;
    private Aluno aluno;
    private boolean foiPaga;

    public Cobranca(Aluno aluno, double valorTotal){
        this.valorTotal = valorTotal;
        this.aluno = aluno;
    }

    public void cobrarFatura() {
        this.foiPaga = true;
        // this.valorTotal = aluno.getMatriculas().stream()
        //         .map(matricula -> matricula.getDisciplina().getPreco())
        //         .mapToDouble(Double::doubleValue).sum();
    }

    public void addValorTotal(double valor){
        this.valorTotal += valor;
    }
}
