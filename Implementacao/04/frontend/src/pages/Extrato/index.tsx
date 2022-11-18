import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { TransacaoModel } from "../../models/TransacaoModel";
import { TransacoesRepository } from "../../repositories/TransacoesRepository";
import { ExtratoContainer, ExtratoTable } from "./styles";
import { format, parseISO } from 'date-fns';

export function Extrato () {
    const { user } = useContext(UserContext);
    const [transacoes, setTransacoes] = useState<TransacaoModel[]>([]);

    useEffect(() => {
        obterTransacoesUsuario()
    }, []);

    async function obterTransacoesUsuario() {
        if (user) {
            console.log('if')
            const resposta = await TransacoesRepository.ObterTransacoes(user.idUsuario);
            if (resposta) {
                setTransacoes(resposta.transacoes)
            }
            else {
                alert('Erro ao tentar obter as transações')
            }
        }
    }


    return (
        <ExtratoContainer>
            <h1>Extrato</h1>
            <ExtratoTable>
            <thead>
                    <tr>
                        <th>Valor</th>
                        <th>Anotações</th>
                        <th>Data da transação</th>
                    </tr>
                </thead>
                <tbody>
                    {transacoes.map(transacao => {
                        const dataISO = parseISO(transacao.dataTransacao);
                        const dataFormatada = format(dataISO, 'dd/MM/yyyy')
                        return (
                            <tr key={transacao.idTransacao}>
                                <td>{transacao.preco} moedas</td>
                                <td>{transacao.anotacao}</td>
                                <td>{dataFormatada}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </ExtratoTable>
        </ExtratoContainer>
    )
}