import { HeaderContainer, HeaderContent, UserActionsAndInfo, UserProfile } from "./styles";
import { SignOut } from 'phosphor-react';
import { UserContext } from "../../context/UserContext";
import { useContext } from "react";

export function Header() {
    const { logoutUser, user } = useContext(UserContext);

    return (
        <HeaderContainer>
            <HeaderContent>
                <h3>Sistema de moeda estudantil</h3>
                {user && (
                    <UserActionsAndInfo>
                        <UserProfile>
                            <span>Seja bem vindo,</span>
                            <strong>{user.nome} | <em>{user.perfil}</em></strong>
                        </UserProfile>
                        <button 
                            type="button" 
                            onClick={logoutUser}
                            title="Sair da sessão"
                        >
                            <SignOut size={32} />
                        </button>
                    </UserActionsAndInfo>
                )}
            </HeaderContent>
        </HeaderContainer>
    );
}