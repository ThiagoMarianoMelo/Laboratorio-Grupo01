import java.util.List;

public class Secretario extends Usuario{

    Secretaria secretaria;

    public void cadastrarAluno(Curso curso, String senha, String email){
        final Aluno aluno = new Aluno(nome, senha, curso);
        secretaria.addAluno(aluno);
    }

    public void cadastrarProfessor(Curso curso, String senha, String email){
        final Professor professor = new Professor(nome, senha);
        secretaria.addProfessor(professor);
    }

    public void cadastrarDisciplina(String nome, Professor professor, List<Curso> cursos ){
    }

}
