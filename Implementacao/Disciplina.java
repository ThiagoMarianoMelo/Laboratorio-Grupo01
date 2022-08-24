import java.util.HashMap;
import java.util.List;

public class Disciplina {
    private TipoDisciplina tipoDisciplina;
    private boolean statusDisciplina;
    private boolean statusMatricula;
    private HashMap<Aluno,String> alunos;
    private Professor professor;
    private List<Curso> cursos;
    private double preco;

    public boolean finalizarInscricoes(){return true;}
    public boolean cancelarDisciplina(){return true;}
}
