import java.time.LocalDate;
import java.util.List;

public class Curriculo {
    private Curso curso;
    private List<Disciplina> disciplinasDoCurso;
    private LocalDate matriculainicio;
    private LocalDate matriculaFim;

    public Curriculo(Curso c, List<Disciplina> disciplinasDoCurso, LocalDate inicio, LocalDate Fim){
        this.curso = c;
        this.disciplinasDoCurso = disciplinasDoCurso;
        this.matriculainicio = inicio;
        this.matriculaFim = Fim;
    }

    public LocalDate getMatriculainicio() {
        return matriculainicio;
    }

    public LocalDate getMatriculaFim() {
        return matriculaFim;
    }

    public List<Disciplina> getDisciplinasDoCurso() {
        return disciplinasDoCurso;
    }
}
