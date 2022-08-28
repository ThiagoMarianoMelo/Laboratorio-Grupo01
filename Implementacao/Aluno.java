

import java.time.LocalDate;
import java.util.List;

public class Aluno extends Usuario {
    private List<MatriculaDisciplina> matriculas;
    private Curso curso;
    private List<Cobranca> cobrancas;

    public void matricularEmDisciplina(Disciplina disciplina, LocalDate data){
        if(disciplina.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA){
            if(this.matriculas.stream()
                                .filter(matricula -> matricula.getStatusMatricula() == true)
                                .filter(matricula -> matricula.getDisciplina().getTipoDisciplina() == TipoDisciplina.OBRIGATORIA)
                                .count()<TipoDisciplina.OBRIGATORIA.getQuantidadeDeMateriasPossiveis()){
                                    MatriculaDisciplina m = new MatriculaDisciplina(this, disciplina, data);
                                    if(m.getStatusMatricula() == true)
                                            System.out.println("Matrícula Efetuada Com Sucesso!");
        }
        else
            System.out.println("Matrícula não completada. Motivo: Você atingiu o número máximo de disciplinas obrigatórias matriculadas.");
        }
        else{
            if(this.matriculas.stream()
                                .filter(matricula -> matricula.getStatusMatricula() == true)
                                .filter(matricula -> matricula.getDisciplina().getTipoDisciplina() == TipoDisciplina.OPCIONAL)
                                .count()<TipoDisciplina.OPCIONAL.getQuantidadeDeMateriasPossiveis())
                                {
                                    MatriculaDisciplina m = new MatriculaDisciplina(this, disciplina, data);
                                    if(m.getStatusMatricula() == true)
                                            System.out.println("Matrícula Efetuada Com Sucesso!");
        }
        else
            System.out.println("Matrícula não completada. Motivo: Você atingiu o número máximo de disciplinas opcionais matriculadas.");
        }
    }

    public void cancelarMatriculaEmDisciplina(Disciplina disciplina, LocalDate date){

    }

    private boolean possuiDisciplina(Disciplina disciplina){
        for(int i=0; i<=this.matriculas.size();i++){
            if((matriculas.get(i).getDisciplina().equals(disciplina)) == true)
                return true;
            }
            return false;
        }


    public void pagarFatura(){}

    public Curso getCurso() {
        return curso;
    }

}
