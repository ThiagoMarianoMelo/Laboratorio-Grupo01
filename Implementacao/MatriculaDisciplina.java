import java.time.LocalDate;



public class MatriculaDisciplina {
    private Aluno aluno;
    private Disciplina disciplina;
    private boolean statusMatricula;
    private LocalDate dataMatricula;

    public MatriculaDisciplina(Aluno aluno, Disciplina disciplina, LocalDate data){
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.dataMatricula = data;
        this.efetivarMatriculaDisciplina();

    }

    private boolean checarDisciplinaExistenteNoCurso(){
        boolean temDisciplina = this.aluno.getCurso().getCurriculoDeCurso().getDisciplinasDoCurso().stream().anyMatch(Disciplina -> {
            return Disciplina.equals(this.disciplina);
        });
            return temDisciplina;
        /*for (int i=0; i<=this.aluno.getCurso().getDisciplinas().size(); i++){
            if(this.aluno.getCurso().getDisciplinas().get(i).equals(this.disciplina) ==true)
                return true;
            else
                System.out.println("Matrícula não completada. Motivo: Essa disciplina não existe no seu curso.");
                return false;
        } */
    }

    private boolean checarPeriodoMatricula(){
            if(this.dataMatricula.isBefore(this.aluno.getCurso().getCurriculoDeCurso().getMatriculaFim()) && this.dataMatricula.isAfter(this.aluno.getCurso().getCurriculoDeCurso().getMatriculainicio()) )
                return true;
            else
            System.out.println("Matrícula não completada. Motivo:O periodo para inscrição nessa disciplina já acabou.");
                return false;
    }

    private boolean checarQtdeAlunosDisciplina(){
        if(this.disciplina.getAlunos().size()==0){
            return true;
        }
        else{
            if(this.disciplina.getAlunos().size()<60)
                return true;
            else
                System.out.println("Matrícula não completada. Motivo:Essa Disciplina alcançou a quantidade máxima de alunos");
            return false;
        }
        }


    private void efetivarMatriculaDisciplina(){
        if(this.checarPeriodoMatricula()==true && this.checarQtdeAlunosDisciplina()==true && this.checarDisciplinaExistenteNoCurso()==true){
            this.statusMatricula = true;
            this.disciplina.adicionarAluno(this.aluno);
        }
        else
            this.statusMatricula = false;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public boolean getStatusMatricula(){
        return this.statusMatricula;
    }

}
