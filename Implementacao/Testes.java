

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class Testes {

    static Professor p;
    static Disciplina d1;
    static Disciplina d2;
    static Disciplina d3;
    static Disciplina d4;
    static Disciplina d5;
    static Disciplina d6;
    static Disciplina d7;
    static Disciplina d8;
    static Curso c;
    static Aluno a;
    static Aluno a1;
    static Aluno a2;
    static Aluno a3;
    static Aluno a4;
    static ArrayList<Disciplina> disciplinas;
    static Curriculo curriculo;
    static Secretaria s;

    @BeforeEach
    public void Init(){
        s = new Secretaria();
        p = new Professor("Laerte", "123123");
        a1 = new Aluno("Pedro", "123456", c);
        a2 = new Aluno("Thiago", "123456", c);
        a3 = new Aluno("Julia", "123456", c);
        a4 = new Aluno("Felipe", "123456", c);
        d1 = new Disciplina("Projeto de Software", TipoDisciplina.OBRIGATORIA, p, 400);
        d2 = new Disciplina("Laboratório de Software", TipoDisciplina.OBRIGATORIA, p, 400);
        d3 = new Disciplina("Trabalho Interdisciplinar", TipoDisciplina.OBRIGATORIA, p, 400);
        d4 = new Disciplina("Redes de Computadores", TipoDisciplina.OBRIGATORIA, p, 400);
        d5 = new Disciplina("Cálculo 2", TipoDisciplina.OBRIGATORIA, p, 200);
        d6 = new Disciplina("Cálculo 1", TipoDisciplina.OPCIONAL, p, 200);
        d7 = new Disciplina("Grafos", TipoDisciplina.OPCIONAL, p, 200);
        d8 = new Disciplina("Interação Humano Computador", TipoDisciplina.OPCIONAL, p, 200);
        disciplinas = new ArrayList<>();
        disciplinas.add(d1);
        disciplinas.add(d2);
        disciplinas.add(d3);
        disciplinas.add(d4);
        disciplinas.add(d5);
        disciplinas.add(d6);
        disciplinas.add(d7);
        disciplinas.add(d8);
        //d1.adicionarAluno(a1);
        //d1.adicionarAluno(a2);
        //d1.adicionarAluno(a3);
        //d1.adicionarAluno(a4);
        c = new Curso("Engenharia de Software", 600);
        s.gerarCurriculo(c, disciplinas, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
        a = new Aluno("Matheus Nolasco", "123456", c);
    }


    //Início Testes na Classe Aluno

    @Test
    public void testarMatricularEmDisciplina(){
        this.Init();
        a.matricularEmDisciplina(d1, LocalDate.now());
        assertEquals(a.getMatriculas().get(0).getDisciplina(), d1);
    }


    @Test
    public void testarAlunoAdicionadoEmDisciplina(){
        this.Init();
        d1.adicionarAluno(a1);
    }

    @Test
    public void testarCriacaoDeEmail(){
        this.Init();
        assertEquals(a.getEmail(), "MatheusNolasco@faculdade.com");
    }

    @Test
    public void testarMatricularEmDisciplinaComMaisDe4Obrigatorias(){
        this.Init();
        a.matricularEmDisciplina(d1, LocalDate.now()); //Matrícula na primeira matéria funcionando
        a.matricularEmDisciplina(d2, LocalDate.now()); //Matrícula na segunda matéria funcionando
        a.matricularEmDisciplina(d3, LocalDate.now()); //Matrícula na terceira matéria funcionando
        a.matricularEmDisciplina(d4, LocalDate.now()); //Matrícula na quarta matéria funcionando
        a.matricularEmDisciplina(d5, LocalDate.now()); //Matrícula não pertmitida, visto que, já existem 4 matrículas obrigatórias com esse aluno
    }

    @Test
    public void testarMatricularEmDisciplinaComMaisDe2Opicionais(){
        this.Init();
        a.matricularEmDisciplina(d6, LocalDate.now()); //Matrícula na primeira matéria funcionando
        a.matricularEmDisciplina(d7, LocalDate.now()); //Matrícula na segunda matéria funcionando
        a.matricularEmDisciplina(d8, LocalDate.now()); //Matrícula na terceira matéria funcionando
    }

    @Test
    public void testarCancelarMatricula(){
        this.Init();
        a.matricularEmDisciplina(d6, LocalDate.now()); //Matrícula na primeira matéria funcionando
        a.matricularEmDisciplina(d7, LocalDate.now()); //Matrícula na segunda matéria funcionando
        a.cancelarMatriculaEmDisciplina(d7, LocalDate.now()); //Cancelamento efetuado com sucesso
        a.matricularEmDisciplina(d8, LocalDate.now()); //Matrícula na segunda matéria funcionando
    }

    //Fim Testes na Classe Aluno
}
