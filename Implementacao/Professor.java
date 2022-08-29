import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasAtuais;

    public Professor(String nome,String senha){
        this.disciplinasAtuais = new ArrayList<>();
        this.nome = nome;
        this.senha = senha;
        this.gerarEmail();
    }

    public void relatorioAlunosDisciplina(Disciplina disciplina, Aluno aluno){}

    public void addDisciplina(Disciplina d){
        this.disciplinasAtuais.add(d);
    }


}
