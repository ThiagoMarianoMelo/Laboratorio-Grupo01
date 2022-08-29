import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Secretaria {
    private List<Professor> professores;
    private HashMap<Aluno,String> alunos;
    private List<Disciplina> disciplinas;
    private List<Curriculo> curriculos;

    public Secretaria(){
        this.professores = new ArrayList<>();
        this.alunos = new HashMap<>();
        this.disciplinas = new ArrayList<>();
        this.curriculos = new ArrayList<>();
    }

    public void gerarCurriculo(Curso curso, List<Disciplina> disciplinas,LocalDate dataMatriculaDisciplinaInicio,LocalDate dataMatriculaDisciplinaFim){
        Curriculo c = new Curriculo(curso, disciplinas,dataMatriculaDisciplinaInicio, dataMatriculaDisciplinaFim);
        curso.setCurriculoDeCurso(c);
    }
}
