import styled from "styled-components";

export const ExtratoContainer = styled.div`
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

export const ExtratoTable = styled.table`
    padding: 0.5rem;
    border-collapse: collapse;
    margin: 0 auto;
    width: 100%;
    
    th {
        padding: 0.5rem;
    }

    td {
        padding: 1rem;
    }

    td,th {
        border: 1px solid ${props => props.theme["purple-500"]};    
        text-align: center;
    }
`;