import { useContext, useEffect } from "react";
import { UserContext } from "../../context/UserContext";
import { HomeContainer, VantagensTable } from "./styles";

export function Home() {
    const { verifyIfUserIsLoggedIn } = useContext(UserContext);
    
    useEffect(() => {
        verifyIfUserIsLoggedIn();
    }, []);

    return (
        <HomeContainer>
            <h1>Vantagens:</h1>
            <VantagensTable>
                <tr>
                    <td></td>
                    <td>Empresa</td>
                    <td>Descrição</td>
                </tr>
            </VantagensTable>
        </HomeContainer>
    );
}