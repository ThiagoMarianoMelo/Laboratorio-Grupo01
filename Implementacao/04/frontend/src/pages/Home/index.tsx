import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { PerfilUsuario } from "../../models/UserModel";
import { VantagensModel } from "../../models/VantagensModel";
import { VantagensRepositorio } from "../../repositories/VantagensRepository";
import { ButtonAction, ButtonsContainer, HomeContainer, VantagensTable } from "./styles";

export function Home() {
    const { user, verifyIfUserIsLoggedIn } = useContext(UserContext);
    const [vantagens, setVantagens] = useState<VantagensModel[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        verifyIfUserIsLoggedIn();
        handleGetAllVantagens();
    }, []);

    async function handleGetAllVantagens() {
        const response = await VantagensRepositorio.ListVantagens();
        setVantagens(response);
    }

    function handleRedirectToCreateVantagem() {
        navigate('/vantagens/cadastrar');
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
                        const priceInBRL = vant.preco.toLocaleString('pt-br', {minimumFractionDigits: 2})
                        return (
                            <tr key={vant.idVantagem}>
                                <td>
                                    <img src={vant.urlFoto} alt="" />
                                    <strong>{vant.nome}</strong>
                                </td>
                                <td>{vant.empresa}</td>
                                <td>{vant.descricao}</td>
                                <td>R$ {priceInBRL}</td>
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