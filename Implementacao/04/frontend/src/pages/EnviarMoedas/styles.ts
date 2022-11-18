import styled from "styled-components";

export const EnviarMoedasContainer = styled.div`
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

export const EnviarMoedasTable = styled.table`
    padding: 0.5rem;
    border-collapse: collapse;
    margin: 0 auto;
    width: 100%;
    
    th {
        padding: 0.5rem;
    }

    td {
        padding: 1rem;

        &:first-child {
            width: 50%;
            text-align: left;
            position: relative;

            strong {
                top: 50%;
                transform: translateY(-50%);
                position: absolute;
                color: ${props => props.theme["purple-700"]};
                cursor: pointer;
                transition: color 0.2s;
                &:hover {
                    color: ${props => props.theme["purple-500"]};
                    text-decoration: underline;
                }
            }

            img {
                width: 64px;
                height: 64px;
                margin-right: 1rem;
                border-radius: 6px;
            }
        }

    }

    td,th {
        border: 1px solid ${props => props.theme["purple-500"]};    
        text-align: center;
    }
`;