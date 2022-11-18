import { FormEvent, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { UserRepository } from "../../repositories/UserRepository";
import { InputField, InputFieldsContainer, RegisterContainer, RegisterContent, SelectTypeForm } from "./styles";

export interface IRegisterUserRequest {
    cpf: string;
    senha: string;
    nome: string;
    perfilId: number;
    email: string;
}

export function Register() {
    const ID_PERFIL_EMPRESA = 3;

    const { user, saveUser } = useContext(UserContext);
    const navigate = useNavigate();

    const [cpf, setCpf] = useState('');
    const [password, setPassword] = useState('');
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('')
    const [rg, setRg] = useState('')

    const [visualizarCadastroEmpresa, setVisualizarCadastroEmpresa] = useState(false);

    useEffect(() => {
        setNome('')
        setPassword('')
        setCpf('')
        setEmail('');
        setRg('');
    }, [visualizarCadastroEmpresa])

    function handleRedirectToLogin() {
        navigate('/login');
    }

    async function registrarUsuario(event: FormEvent) {
        event.preventDefault();
        const request: IRegisterUserRequest = {
            cpf,
            nome,
            senha: password,
            perfilId: visualizarCadastroEmpresa ? 3 : 2,
            email
        };
        console.log(request);
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
                <h1>Cadastrar</h1>
                <SelectTypeForm>

                    <InputField>
                        <label htmlFor="aluno">Aluno</label>
                        <input name="aluno" type="radio" defaultChecked onChange={(e) => setVisualizarCadastroEmpresa(false)}/>
                    </InputField>
                    <InputField>
                        <label htmlFor="aluno">Empresa</label>
                        <input name="aluno" type="radio" onChange={(e) => setVisualizarCadastroEmpresa(true)}/>
                    </InputField>
                </SelectTypeForm>


                {visualizarCadastroEmpresa ? (
                    <>
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
                            <label htmlFor="email">Email:</label>
                            <input 
                                type="email" 
                                name="email" 
                                placeholder="Digite seu email" 
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}    
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
                    </>
                ) : (
                    <>
                    <InputFieldsContainer action="" onSubmit={registrarUsuario}>
                        <InputField>
                            <label htmlFor="email">Email:</label>
                            <input 
                                type="email" 
                                name="email" 
                                placeholder="Digite seu Email" 
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}    
                            />
                        </InputField>
                        <InputField>
                            <label htmlFor="Cpf">CPF:</label>
                            <input 
                                type="Cpf" 
                                name="Cpf" 
                                placeholder="Digite seu Cpf" 
                                value={cpf}
                                onChange={(e) => setCpf(e.target.value)}    
                            />
                        </InputField>
                        <InputField>
                            <label htmlFor="RG">RG:</label>
                            <input 
                                type="RG" 
                                name="RG" 
                                placeholder="Digite seu RG" 
                                value={rg}
                                onChange={(e) => setRg(e.target.value)}    
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
                    </>

                )}
            </RegisterContent>
        </RegisterContainer>
    )
}