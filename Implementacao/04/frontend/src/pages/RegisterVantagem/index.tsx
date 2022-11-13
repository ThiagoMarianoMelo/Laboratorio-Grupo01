import { FormEvent, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { VantagensRepositorio } from "../../repositories/VantagensRepository";
import { InputField, InputFieldsContainer, RegisterContainer, RegisterContent } from "./styles";

export interface IRegisterVantagemRequest {
    idEmpresa: number;
    descricao: string;
    nome: string;
    urlFoto: string;
    preco: number;
}

export function RegisterVantagem() {
    const { user, verifyIfUserIsLoggedIn } = useContext(UserContext);
    const navigate = useNavigate();

    useEffect(() => {
        verifyIfUserIsLoggedIn()
    }, []);

    const [nome, setNome] = useState('');
    const [descricao, setDescricao] = useState('');
    const [urlFoto, setUrlFoto] = useState('');
    const [preco, setPreco] = useState('');

    function handleRedirectToHome() {
        navigate('/');
    }

    async function handleRegisterUser(event: FormEvent) {
        event.preventDefault();

        const request: IRegisterVantagemRequest = {
            descricao,
            urlFoto,
            preco: Number(preco),
            nome,
            idEmpresa: user?.idUsuario ?? 0
        };

        const vantagemCreatedSucessfully = await VantagensRepositorio.CadastrarVantagem(request);
        
        if (vantagemCreatedSucessfully !== 0) {
            handleRedirectToHome();
        }
        else {
            alert('Erro ao cadastrar vantagem!');
        }
    }

    return (
        <RegisterContainer>
            <RegisterContent>
                <h1>Cadastrar Vantagem</h1>
                <InputFieldsContainer action="" onSubmit={handleRegisterUser}>
                    <InputField>
                        <label htmlFor="nome">Nome:</label>
                        <input 
                            type="text" 
                            name="nome" 
                            placeholder="Digite seu nome" 
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}    
                        />
                    </InputField>
                    <InputField>
                        <label htmlFor="preco">Preço:</label>
                        <input 
                            type="text" 
                            name="preco" 
                            placeholder="Digite o preço do seu produto" 
                            value={preco}
                            onChange={(e) => setPreco(e.target.value)}    
                        />
                    </InputField>
                    <InputField>
                        <label htmlFor="preco">URL Foto:</label>
                        <input 
                            type="url" 
                            name="url" 
                            placeholder="https://" 
                            value={urlFoto}
                            onChange={(e) => setUrlFoto(e.target.value)}    
                        />
                    </InputField>
                    <InputField>
                        <label htmlFor="descricao">Descricao:</label>
                        <textarea 
                            name="descricao" 
                            placeholder="Descreva seu produto" 
                            onChange={(e) => setDescricao(e.target.value)} 
                            value={descricao}    
                        />
                    </InputField>
                    <button type="submit">Cadastrar Vantagem</button>
                    <button type="button" onClick={handleRedirectToHome}>Voltar</button>
                </InputFieldsContainer>
            </RegisterContent>
        </RegisterContainer>
    )
}