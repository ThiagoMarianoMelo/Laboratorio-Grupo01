import styled from "styled-components";

export const HomeContainer = styled.main`
    margin: 2rem auto;
    background: ${props => props.theme["gray-300"]};
    width: 1120px;
    padding: 1rem 2rem;
    border-radius: 6px;

    h1 { 
        color: ${props => props.theme["gray-800"]};
        margin-bottom: 1rem;
    }
`

export const VantagensTable = styled.table`
    padding: 0.5rem;
    border-spacing: 1rem;

    td {
        &:first-child {
            width: 50%;
        }
    }
`;