import java.util.List;

public class Curso {
    private String nome;
    private List<Disciplina> disciplinas;
    private int numeroDeCreditos;
    private Curriculo curriculoDeCurso;

    public void relatorioCurso(){}

    public Curriculo getCurriculoDeCurso() {
        return curriculoDeCurso;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
