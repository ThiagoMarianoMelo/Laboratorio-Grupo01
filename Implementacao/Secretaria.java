import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Secretaria {
    private List<Professor> professores;
    private HashMap<Aluno,String> alunos;
    private List<Disciplina> disciplinas;
    private List<Curriculo> curriculos;

    public void gerarCurriculo(Curso curso, LocalDate dataMatriculaDisciplinaInicio,LocalDate dataMatriculaDisciplinaFim){
    }

    public void cadastrarAluno(Curso curso, String senha, String email){}

    public void cadastrarProfessor(Curso curso, String senha, String email){}

    public void cadastrarCurso(String nome, List<Disciplina> disciplinas, int numeroDeCreditos){}

    public void cadastrarDisciplina(String nome, Professor professor, List<Curso> cursos ){}
}
