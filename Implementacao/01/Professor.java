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

    public String relatorioAlunosDisciplina(Disciplina disciplina){
        final boolean estaLecionandoDisciplina = disciplinasAtuais.stream().anyMatch(d -> d.equals(disciplina));
        if(estaLecionandoDisciplina){
            return disciplina.getAlunos().stream().toString();
        }
        return null;
    }

    public void addDisciplina(Disciplina d){
        this.disciplinasAtuais.add(d);
    }


}
