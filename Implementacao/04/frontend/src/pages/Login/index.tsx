import { FormEvent, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { UserRepository } from "../../repositories/UserRepository";
import { InputField, InputFieldsContainer, LoginContainer, LoginContent } from "./styles";

export interface ILoginUserRequest {
    cpf: string;
    senha: string;
}

export function Login() {
    const { user, saveUser } = useContext(UserContext);
    const navigate = useNavigate();

    const [cpf, setCpf] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        if (user) {
            navigate('/');
        }
    }, [])

    function handleRedirectToRegister() {
        navigate('/register');
    }

    async function fazerLogin(event: FormEvent) {
        event.preventDefault();

        const request: ILoginUserRequest = {
            cpf,
            senha: password
        };

        const userFound = await UserRepository.Login(request);
        
        if (userFound) {
            saveUser(userFound);
            navigate('/');
        }
        else {
            alert('Usuário não encontrado!');
        }
    }

    return (
        <LoginContainer>
            <LoginContent>
                <h1>Entrar</h1>
                <InputFieldsContainer action="" onSubmit={fazerLogin}>
                    <InputField>
                        <label htmlFor="cpf">CPF:</label>
                        <input 
                            type="text" 
                            name="cpf" 
                            placeholder="Digite seu CPF" 
                            value={cpf}
                            onChange={(e) => setCpf(e.target.value)}    
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
                    <button type="submit">Acessar conta</button>
                    <button type="button" onClick={handleRedirectToRegister}>Cadastrar</button>
                </InputFieldsContainer>
            </LoginContent>
        </LoginContainer>
    )
}