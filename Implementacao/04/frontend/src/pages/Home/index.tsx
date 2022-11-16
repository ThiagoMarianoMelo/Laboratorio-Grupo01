import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { PerfilUsuario } from "../../models/UserModel";
import { VantagensModel } from "../../models/VantagensModel";
import { AlunoRepository } from "../../repositories/AlunoRepository";
import { VantagensRepositorio } from "../../repositories/VantagensRepository";
import { ButtonAction, ButtonsContainer, HomeContainer, VantagensTable } from "./styles";

export interface IBuyVantagemRequest {
    idVantagem: number;
    idAluno: number;
}

export function Home() {
    const { user, verifyIfUserIsLoggedIn, atualizarSaldo } = useContext(UserContext);
    const [vantagens, setVantagens] = useState<VantagensModel[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        verifyIfUserIsLoggedIn();
        obterTodasAsVantagens();
    }, []);

    async function obterTodasAsVantagens() {
        const response = await VantagensRepositorio.ListarVantagens();
        setVantagens(response);
    }

    function handleRedirectToCreateVantagem() {
        navigate('/vantagens/cadastrar');
    }

    async function comprarVantagem(vantagem: VantagensModel) {
        if (user?.perfil === PerfilUsuario.ALUNO) {
            const confirmation = confirm(`Você tem certeza que quer comprar ${vantagem.nome} por ${vantagem.preco} moedas`);
            
            if (confirmation) {
                const request: IBuyVantagemRequest = {
                    idAluno: user.idUsuario,
                    idVantagem: vantagem.idVantagem
                }
    
                try {
                    const boughtSuccessfully = await AlunoRepository.ComprarVantagem(request);
                    if (boughtSuccessfully) {
                        atualizarSaldo(vantagem.preco);
                        alert('Vantagem comprada com sucesso');
                        window.location.reload();
                    }
                }
                catch(err) {
                    alert('Saldo insuficiente');
                }
            }
        }
    }

    return user && (
        <HomeContainer>
            <h1>Vantagens:</h1>
            <VantagensTable>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Empresa</th>
                        <th>Descrição</th>
                        <th>Preço</th>
                    </tr>
                </thead>
                <tbody>
                    {vantagens.map(vant => {
                        return (
                            <tr key={vant.idVantagem}>
                                <td>
                                    <img src={vant.urlFoto} alt="" />
                                    <button onClick={() => comprarVantagem(vant)} type="button">
                                        <strong>{vant.nome}</strong>
                                    </button>
                                </td>
                                <td>{vant.empresa}</td>
                                <td>{vant.descricao}</td>
                                <td>{vant.preco} moedas</td>
                            </tr>
                        )
                    })}
                </tbody>
            </VantagensTable>
            {user.perfil === PerfilUsuario.EMPRESA && 
                <ButtonsContainer>
                    <ButtonAction type="button" onClick={handleRedirectToCreateVantagem}>Cadastrar vantagem</ButtonAction>
                </ButtonsContainer>
            }
        </HomeContainer>
    );
}