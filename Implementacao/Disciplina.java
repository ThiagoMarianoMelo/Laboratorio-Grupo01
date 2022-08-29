import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Disciplina{
    private String nome;
    private TipoDisciplina tipoDisciplina;
    private boolean statusDisciplina;
    private boolean statusMatricula;
    private List<Aluno> alunos;
    private Professor professor;
    private List<Curso> cursos;
    private double preco;

    public Disciplina(String nome,TipoDisciplina tipo, Professor p, double preco ){
        this.alunos = new ArrayList<>();
        this.nome = nome;
        this.tipoDisciplina = tipo;
        this.professor = p;
        this.preco = preco;
        this.statusDisciplina = true;
        this.statusMatricula = true;
    }

    public boolean finalizarInscricoes(){return true;}
    public boolean cancelarDisciplina(){return true;}

    public String getNome() {
        return nome;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void adicionarCurso(Curso curso){
        this.cursos.add(curso);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public TipoDisciplina getTipoDisciplina() {
        return tipoDisciplina;
    }
}
