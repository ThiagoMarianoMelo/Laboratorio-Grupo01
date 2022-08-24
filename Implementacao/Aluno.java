import java.time.LocalDate;
import java.util.List;

public class Aluno {
    private List<MatriculaDisciplina> matriculas;
    private Curso curso;
    private int MAX_DISCIPLINAS_MATRICULADAS=6;
    private List<Cobranca> cobrancas;

    public void matricularEmDisciplina(Disciplina disciplina, LocalDate data){}
    public void cancelarMatriculaEmDisciplina(Disciplina disciplina, LocalDate date){}
    private boolean possuiDisciplina(Disciplina disciplina){ return true;}
    public void pagarFatura(){}
}
