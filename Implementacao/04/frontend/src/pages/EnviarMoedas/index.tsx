import { PaperPlaneTilt, X } from "phosphor-react";
import { FormEvent, useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { UserModel } from "../../models/UserModel";
import { AlunoRepository } from "../../repositories/AlunoRepository";
import { ProfessorRepository } from "../../repositories/ProfessorRepository";
import { EnviarMoedasContainer, EnviarMoedasTable, InputsArea, Modal, ModalOverlay, MoedasContainer, TextAreaContainer } from "./styles";
import * as ReactModal from 'react-modal';

export interface IEnviarMoedasRequest {
    idProfessor: number;
    idAluno: number;
    valorEmMoedas: number;
    anotacao: string;
}

export function EnviarMoedas() {
    const { user,atualizarSaldo, verifyIfUserIsLoggedIn } = useContext(UserContext);
    const [alunos, setAlunos] = useState<UserModel[]>([])
    const [anotacao, setAnotacao] = useState('')
    const [valorEmMoedas, setValorEmMoedas] = useState('')
    const [exibirModal, setExibirModal] = useState(false);
    const [alunoSelecionado, setAlunoSelecionado] = useState<UserModel | null>(null);

    useEffect(() => {
        verifyIfUserIsLoggedIn()
        obterAlunos()
    }, []);

    useEffect(() => {
        if (alunoSelecionado) {
            setExibirModal(true);
        }
    }, [alunoSelecionado])

    async function obterAlunos() {
        const response = await AlunoRepository.ObterAlunos();
        if (response) {
            setAlunos(response)
        }else {
            alert('Erro ao obter alunos')
        }
    }

    async function enviarMoedasParaUsuario(event: FormEvent) {
        event.preventDefault();
        if (user && alunoSelecionado) {
            const request: IEnviarMoedasRequest = {
                idAluno: alunoSelecionado?.idUsuario,
                idProfessor: user.idUsuario,
                anotacao,
                valorEmMoedas: Number(valorEmMoedas)
            }
    
            const response = await ProfessorRepository.EnviarMoedas(request);
            if (response) {
                atualizarSaldo(Number(valorEmMoedas));
                alert(`Moedas enviadas com sucesso para o usuário ${alunoSelecionado.nome}`)
                window.location.reload();
            }
            else {
                alert('Saldo insuficiente');
            }
        }
    }

    function fecharModal() {
        setAlunoSelecionado(null)
        setAnotacao('')
        setValorEmMoedas('')
        setExibirModal(false)
    }

    const botaoDesativado = anotacao.length === 0 || valorEmMoedas.length === 0

    return (
        <EnviarMoedasContainer>
            <h1>Enviar moedas para aluno:</h1>
            <EnviarMoedasTable>
            <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {alunos.map(aluno => {
                        return (
                            <tr key={aluno.idUsuario}>
                                <td>{aluno.nome}</td>
                                <td>{aluno.email}</td>
                                <td>
                                    <button title="Enviar moedas para o aluno"onClick={() => setAlunoSelecionado(aluno)}>
                                        <PaperPlaneTilt size={32}/>
                                    </button>
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
        </EnviarMoedasTable>
        {exibirModal && (
            <>
                <ModalOverlay/>
                <Modal onSubmit={enviarMoedasParaUsuario}>
                    <header>
                        <h1>Enviar moedas para: {alunoSelecionado?.nome}</h1>
                        <X size={24} onClick={fecharModal}/>
                    </header>
                    <InputsArea>
                        <MoedasContainer>
                            <label htmlFor="moedas">Quantidade de moedas:</label>
                            <input placeholder="Digite um valor em moedas" type="text" value={valorEmMoedas} onChange={(e) => setValorEmMoedas((e.target.value))} name="moedas" />
                        </MoedasContainer>
                        <TextAreaContainer>
                            <label htmlFor="anotacoes">Anotações:</label>
                            <textarea placeholder="Digite uma anotação para o aluno"name="anotacoes" onChange={(e) => setAnotacao(e.target.value)} value={anotacao}/>
                        </TextAreaContainer>
                    </InputsArea>
                    <button type="submit" disabled={botaoDesativado}>Enviar moedas</button>
                </Modal>
            </>
        )}
            
    </EnviarMoedasContainer>
    )
}