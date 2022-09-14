import java.util.List;

public class Curso {
    private String nome;
    private int numeroDeCreditos;
    private Curriculo curriculoDeCurso;

    public Curso(String nome, int numeroDeCreditos) {
        this.nome = nome;
        this.numeroDeCreditos = numeroDeCreditos;
    }

    public void relatorioCurso() {
    }

    public Curriculo getCurriculoDeCurso() {
        return curriculoDeCurso;
    }

    public void setCurriculoDeCurso(Curriculo curriculoDeCurso) {
        this.curriculoDeCurso = curriculoDeCurso;
    }

}
