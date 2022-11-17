import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { TransacaoModel } from "../../models/TransacaoModel";
import { TransacoesRepository } from "../../repositories/TransacoesRepository";
import { ExtratoContainer } from "./styles";

export function Extrato () {
    const { user } = useContext(UserContext);
    const [transacoes, setTransacoes] = useState<TransacaoModel[]>([]);

    useEffect(() => {
        obterTransacoesUsuario()
    }, []);

    async function obterTransacoesUsuario() {
        if (user) {
            const resposta = await TransacoesRepository.ObterTransacoes(user.idUsuario);
            if (resposta) {
                setTransacoes(resposta)
            }
            else {
                alert('Erro ao tentar obter as transações')
            }
        }
    }


    return (
        <ExtratoContainer>
            <table>
            <thead>
                    <tr>
                        <th>Valor</th>
                        <th>Enviado por</th>
                        <th>Anotações</th>
                        <th>Data da transação</th>
                    </tr>
                </thead>
                <tbody>
                    {/* {transacoes.map(transacao => {
                        return (
                            <tr key={transacao.idTransacao}>
                                <td>{transacao.preco} moedas</td>
                                <td>{transacao.}</td>
                                <td>{vant.descricao}</td>
                                <td>{vant.preco} moedas</td>
                            </tr>
                        )
                    })} */}
                </tbody>
            </table>
        </ExtratoContainer>
    )
}