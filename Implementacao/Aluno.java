

import java.nio.file.DirectoryStream.Filter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
    private List<MatriculaDisciplina> matriculas;
    private Curso curso;
    private List<Cobranca> cobrancas;

    public Aluno(String nome,String senha, Curso curso){
        this.matriculas = new ArrayList<>();
        this.cobrancas = new ArrayList<>();
        this.nome = nome;
        this.senha = senha;
        this.curso = curso;
        this.gerarEmail();
    }

    public void matricularEmDisciplina(Disciplina disciplina, LocalDate data){
        MatriculaDisciplina matriculaProvisoria = new MatriculaDisciplina(this, disciplina, data);
        if(this.matriculas.size() >0){
            if(this.possuiDisciplina(disciplina) == false){ //Se o aluno não estiver matriculado na Disciplina procede
                if(matriculaProvisoria.getStatusMatricula() == true){ //Se for possível matricular naquela matéria (menos de 60 alunos e dentro do período)
                    if(matriculaProvisoria.getDisciplina().getTipoDisciplina() == TipoDisciplina.OBRIGATORIA){ //Procede caso seja obrigatoria
                        if(this.QtdeDisciplinasObrigatoriasCursando()  < TipoDisciplina.OBRIGATORIA.getQuantidadeDeMateriasPossiveis()){ //Se tiver menos de 4 disciplinas obrigatórias procede
                            this.matriculas.add(matriculaProvisoria);
                            System.out.println("Matrícula Efetuada Com Sucesso na disciplina "+disciplina.getNome()+" !");
                        }
                        else{
                            System.out.println("Matrícula da disciplina "+disciplina.getNome()+" não completada. Motivo: Você atingiu o número máximo de disciplinas obrigatórias matriculadas.");
                        }
                    }
                    else{
                        if(matriculaProvisoria.getDisciplina().getTipoDisciplina() == TipoDisciplina.OPCIONAL){ //Procede caso seja opcional
                            if(this.QtdeDisciplinasOpcionaisCursando()  < TipoDisciplina.OPCIONAL.getQuantidadeDeMateriasPossiveis()){ //Se tiver menos de 2 disciplinas opcionais procede
                                this.matriculas.add(matriculaProvisoria);
                                System.out.println("Matrícula Efetuada Com Sucesso na disciplina "+disciplina.getNome()+" !");
                            }
                            else{
                                System.out.println("Matrícula da disciplina "+disciplina.getNome()+" não completada. Motivo: Você atingiu o número máximo de disciplinas opcionais matriculadas.");
                            }
                        }
                    }
                }
                else{
                    System.out.println("Matrícula da disciplina "+disciplina.getNome()+" não completada. Motivo: Disciplina indisponível no momento.");
                }
            }
            else {
                System.out.println("Matrícula da disciplina "+disciplina.getNome()+" não completada. Motivo: Você já está matriculado nessa disciplina.");
            }
        }
        else {
            this.matriculas.add(matriculaProvisoria);
            System.out.println("Matrícula Efetuada Com Sucesso na disciplina "+disciplina.getNome()+" !");
        }
        /* --Método Antigo--

        if(this.matriculas.size()>0){
            if(disciplina.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA){
                if(this.matriculas.stream()
                                    .filter(matricula -> matricula.getDisciplina().getTipoDisciplina() == TipoDisciplina.OBRIGATORIA)
                                    .count()<=TipoDisciplina.OBRIGATORIA.getQuantidadeDeMateriasPossiveis()){
                                        if(matriculaProvisoria.getStatusMatricula() == true){
                                            this.matriculas.add(matriculaProvisoria);
                                            System.out.println("Matrícula Efetuada Com Sucesso na disciplina "+disciplina.getNome()+" !");
                                        }
            }
                else
                    System.out.println("Matrícula da disciplina "+disciplina.getNome()+" não completada. Motivo: Você atingiu o número máximo de disciplinas obrigatórias matriculadas.");
            }
            else{
                if(this.matriculas.stream()
                                    .filter(matricula -> matricula.getDisciplina().getTipoDisciplina() == TipoDisciplina.OPCIONAL)
                                    .count()<TipoDisciplina.OPCIONAL.getQuantidadeDeMateriasPossiveis())
                                    {
                                        if(matriculaProvisoria.getStatusMatricula() == true){
                                            matriculas.add(matriculaProvisoria);
                                            System.out.println("Matrícula Efetuada Com Sucesso na disciplina "+disciplina.getNome()+" !");
                                        }
        }
        else
            System.out.println("Matrícula da disciplina "+disciplina.getNome()+" não completada. Motivo: Você atingiu o número máximo de disciplinas opcionais matriculadas.");
        }
    }
    else{
        matriculas.add(matriculaProvisoria);
        System.out.println("Matrícula Efetuada Com Sucesso na disciplina "+disciplina.getNome()+" !");

    } */
    }

    public void cancelarMatriculaEmDisciplina(Disciplina disciplina, LocalDate date){
        if(this.matriculas.size() > 0){
            if(possuiDisciplina(disciplina) == true){  //Caso o aluno esteja cursando a disciplina ele procede
                if(date.isAfter(curso.getCurriculoDeCurso().getMatriculainicio()) && date.isBefore(curso.getCurriculoDeCurso().getMatriculaFim())){ //Caso esteja dentro do praso procede
                    for(int i=0;i<this.matriculas.size();i++){
                        if(matriculas.get(i).getDisciplina().equals(disciplina) == true){
                            matriculas.remove(i);
                            System.out.println("Cancelamento da disciplina "+ disciplina.getNome()+" efetuado com sucesso");
                        }
                    }
                }
                else{
                    System.out.println("Cancelamento da disciplina "+ disciplina.getNome()+" não efetuado. Motivo: Fora do praso de cancelamento de matrícula.");
                }
            }
            else{
                System.out.println("Cancelamento da disciplina "+ disciplina.getNome()+" não efetuado. Motivo: Você não está matriculado nessa disciplina.");
            }
        }
        else{
            System.out.println("Cancelamento da disciplina "+ disciplina.getNome()+" não efetuado. Motivo: Você não possui nenhuma disciplina matriculada.");
        }
    }

    private boolean possuiDisciplina(Disciplina disciplina){
        for(int i=0; i<=this.matriculas.size()-1;i++){
            if((matriculas.get(i).getDisciplina().equals(disciplina)) == true && matriculas.get(i).getStatusMatricula() == true)
                return true;
            }
            return false;
        }

    private long QtdeDisciplinasObrigatoriasCursando(){
       /*   --Método sem Stream
       int contadorObrigatorias=0;
        for(int i=0; i<this.matriculas.size();i++){
            if(this.matriculas.get(i).getDisciplina().getTipoDisciplina() == TipoDisciplina.OBRIGATORIA)
                contadorObrigatorias++;
            else{}
        } */
        List<Disciplina> auxDisciplinas = new ArrayList<>();
        this.matriculas.stream().forEach(e -> auxDisciplinas.add(e.getDisciplina()));
        long contadorObrigatorias = auxDisciplinas.stream().filter(disciplina->disciplina.getTipoDisciplina().equals(TipoDisciplina.OBRIGATORIA) ==true).count();
        //System.out.println("Disciplinas Obrigatorias:"+contadorObrigatorias);
        return contadorObrigatorias;
    }

    private long QtdeDisciplinasOpcionaisCursando(){
        List<Disciplina> auxDisciplinas = new ArrayList<>();
        this.matriculas.stream().forEach(e -> auxDisciplinas.add(e.getDisciplina()));
        long contadorOpcionais = auxDisciplinas.stream().filter(disciplina->disciplina.getTipoDisciplina().equals(TipoDisciplina.OPCIONAL) ==true).count();
        //System.out.println("Disciplinas Obrigatorias:"+contadorObrigatorias);
        return contadorOpcionais;
    }


    public void pagarFatura(){}

    public Curso getCurso() {
        return curso;
    }

    public List<MatriculaDisciplina> getMatriculas() {
        return matriculas;
    }

}
