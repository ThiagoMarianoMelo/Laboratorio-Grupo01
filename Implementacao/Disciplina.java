import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Disciplina{
    private String nome;
    private TipoDisciplina tipoDisciplina;
    private boolean statusDisciplina;
    private boolean statusMatricula;
    private HashMap<Aluno,String> alunos;
    private Professor professor;
    private List<Curso> cursos;
    private double preco;

    public boolean finalizarInscricoes(){return true;}
    public boolean cancelarDisciplina(){return true;}

    public String getNome() {
        return nome;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.put(aluno, aluno.email);
    }

    public HashMap<Aluno, String> getAlunos() {
        return alunos;
    }

    public TipoDisciplina getTipoDisciplina() {
        return tipoDisciplina;
    }
}
