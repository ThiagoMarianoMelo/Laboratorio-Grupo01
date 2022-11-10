import { useState } from "react";
import { UserRepository } from "../../repositories/UserRepository";
import { InputField, InputFieldsContainer, LoginContainer, LoginContent } from "./styles";

export interface ILoginUserRequest {
    cpf: string;
    password: string;
}

export function Login() {
    const [cpf, setCpf] = useState('');
    const [password, setPassword] = useState('');

    async function handleLoginUser() {
        const request: ILoginUserRequest = {
            cpf,
            password
        };

        const userFound = await UserRepository.Login(request);
        if (userFound) {
            
        } 
    }

    return (
        <LoginContainer>
            <LoginContent>
                <h1>Entrar</h1>
                <InputFieldsContainer action="">
                    <InputField>
                        <label htmlFor="cpf">CPF:</label>
                        <input type="text" name="cpf" placeholder="Digite seu CPF" />
                    </InputField>
                    <InputField>
                        <label htmlFor="password">Senha:</label>
                        <input type="password" name="password" placeholder="Digite sua senha" />
                    </InputField>
                    <button type="submit">Acessar conta</button>
                </InputFieldsContainer>
            </LoginContent>
        </LoginContainer>
    )
}