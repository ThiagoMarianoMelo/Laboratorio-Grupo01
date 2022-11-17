import { Actions, HeaderContainer, HeaderContent, UserActionsAndInfo, UserProfile } from "./styles";
import { CurrencyCircleDollar, Scroll, SignOut } from 'phosphor-react';
import { UserContext } from "../../context/UserContext";
import { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { PerfilUsuario } from "../../models/UserModel";

export function Header() {
    const { logoutUser, user } = useContext(UserContext);
    const navigate = useNavigate();

    function redirecionarParaExtrato() {
        navigate('/extrato');
    }

    function redirecionarParaHome() {
        navigate('/')
    }

    function redirecionarParaEnvioDeMoedas() {
        navigate('/enviar-moedas')
    }

    const usuarioPossuiPermissaoParaExtrato = user && (user.perfil === PerfilUsuario.PROFESSOR || user.perfil === PerfilUsuario.ALUNO)
    const usuarioPossuiPermissaoParaEnviarMoedas = user && user.perfil === PerfilUsuario.PROFESSOR;

    return (
        <HeaderContainer>
            <HeaderContent>
                <h3 onClick={redirecionarParaHome}>Sistema de moeda estudantil</h3>
                {user && (
                    <UserActionsAndInfo>
                        <UserProfile>
                            <span>Seja bem vindo, <strong>{user.nome} | <em>{user.perfil}</em></strong></span>
                            <strong>{user.saldo} moedas</strong>
                        </UserProfile>
                        <Actions>
                            {usuarioPossuiPermissaoParaEnviarMoedas && 
                                <button 
                                    type="button" 
                                    onClick={redirecionarParaEnvioDeMoedas}
                                    title="Enviar moedas"
                                >
                                    <CurrencyCircleDollar size={32} />
                                </button>
                            }
                            {usuarioPossuiPermissaoParaExtrato && 
                                <button 
                                    type="button" 
                                    onClick={redirecionarParaExtrato}
                                    title="Ver extrato"
                                >
                                    <Scroll size={32} />
                                </button>
                            }
                            <button 
                                type="button" 
                                onClick={logoutUser}
                                title="Sair da sessão"
                            >
                                <SignOut size={32} />
                            </button>
                        </Actions>
                    </UserActionsAndInfo>
                )}
            </HeaderContent>
        </HeaderContainer>
    );
}