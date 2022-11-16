import { FormEvent, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { UserRepository } from "../../repositories/UserRepository";
import { InputField, InputFieldsContainer, RegisterContainer, RegisterContent } from "./styles";

export interface IRegisterUserRequest {
    cpf: string;
    senha: string;
    nome: string;
    perfilId: number;
}

export function Register() {
    const ID_PERFIL_EMPRESA = 3;

    const { user, saveUser } = useContext(UserContext);
    const navigate = useNavigate();

    const [cpf, setCpf] = useState('');
    const [password, setPassword] = useState('');
    const [nome, setNome] = useState('');

    function handleRedirectToLogin() {
        navigate('/login');
    }

    async function registrarUsuario(event: FormEvent) {
        event.preventDefault();

        const request: IRegisterUserRequest = {
            cpf,
            nome,
            senha: password,
            perfilId: ID_PERFIL_EMPRESA,
        };

        const userCreatedSucessfully = await UserRepository.Register(request);
        
        if (userCreatedSucessfully) {
            saveUser(userCreatedSucessfully);
            navigate('/');
        }
        else {
            alert('Erro ao cadastrar usuário!');
        }
    }

    return (
        <RegisterContainer>
            <RegisterContent>
                <h1>Cadastrar Empresa</h1>
                <InputFieldsContainer action="" onSubmit={registrarUsuario}>
                    <InputField>
                        <label htmlFor="cpf">CNPJ:</label>
                        <input 
                            type="text" 
                            name="cpf" 
                            placeholder="Digite seu CNPJ" 
                            value={cpf}
                            onChange={(e) => setCpf(e.target.value)}    
                        />
                    </InputField>
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
                        <label htmlFor="password">Senha:</label>
                        <input 
                            type="password" 
                            name="password" 
                            placeholder="Digite sua senha" 
                            onChange={(e) => setPassword(e.target.value)}
                            value={password}    
                        />
                    </InputField>
                    <button type="submit">Cadastrar</button>
                    <button type="button" onClick={handleRedirectToLogin}>Acessar conta</button>
                </InputFieldsContainer>
            </RegisterContent>
        </RegisterContainer>
    )
}