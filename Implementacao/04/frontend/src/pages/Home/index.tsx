import { HomeContainer, VantagensTable } from "./styles";

export function Home() {
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