import java.io.Serializable;
import java.util.ArrayList;

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

    public boolean finalizarInscricoes(){
        try{
            this.setStatusDisciplina(false);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean cancelarDisciplina(){
        this.setStatusMatricula(false);
        return true;
    }

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
    public List<Curso> getCursos() {
        return cursos;
    }
    public double getPreco() {
        return preco;
    }
    public Professor getProfessor() {
        return professor;
    }
    public boolean isStatusDisciplina() {
        return statusDisciplina;
    }
    public boolean isStatusMatricula() {
        return statusMatricula;
    }
    private void setStatusDisciplina(boolean x){
        this.statusDisciplina = x;
    }
    private void setStatusMatricula(boolean x){
        this.statusMatricula = x;
    }
}
