import java.time.LocalDate;
import java.util.List;

public class Curriculo {
    private Curso curso;
    private List<Disciplina> disciplinasDoCurso;
    private LocalDate matriculainicio;
    private LocalDate matriculaFim;

    public LocalDate getMatriculainicio() {
        return matriculainicio;
    }

    public LocalDate getMatriculaFim() {
        return matriculaFim;
    }
}
