import java.time.LocalDate;
import java.util.List;

public class Secretario extends Usuario {

    Secretaria secretaria;

    public void cadastrarAluno(Curso curso, String senha, String email){}

    public void cadastrarProfessor(Curso curso, String senha, String email){}

    public void cadastrarCurso(String nome, List<Disciplina> disciplinas, int numeroDeCreditos){}

    public void cadastrarDisciplina(String nome, Professor professor, List<Curso> cursos ){}

}
