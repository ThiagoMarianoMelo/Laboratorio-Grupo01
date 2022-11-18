import { PaperPlaneTilt } from "phosphor-react";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { UserModel } from "../../models/UserModel";
import { AlunoRepository } from "../../repositories/AlunoRepository";
import { EnviarMoedasContainer, EnviarMoedasTable } from "./styles";

export function EnviarMoedas() {
    const {user} = useContext(UserContext);
    const [alunos, setAlunos] = useState<UserModel[]>([])

    useEffect(() => {
        obterAlunos()
    }, []);

    async function obterAlunos() {
        const response = await AlunoRepository.ObterAlunos();
        if (response) {
            setAlunos(response)
        }else {
            alert('Erro ao obter alunos')
        }
    }

    async function enviarMoedasParaUsuario(idUsuario: number) {
        if (user) {
            const request = {
                idAluno: idUsuario,
                idProfessor: user.idUsuario ,
                valorQueSeraAdicionado: 
            }
    
            const response = await AlunoRepository.EnviarMoedas(request);
            alert('Moedas enviadas com sucesso')
            window.location.reload();
        }
    }

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
                                    <button onClick={() => enviarMoedasParaUsuario(aluno.idUsuario)}>
                                        <PaperPlaneTilt size={32}/>
                                    </button>
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
        </EnviarMoedasTable>
    </EnviarMoedasContainer>
    )
}