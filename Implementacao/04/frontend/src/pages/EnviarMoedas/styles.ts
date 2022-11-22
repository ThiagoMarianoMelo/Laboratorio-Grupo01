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

    td button {
        background: ${props => props.theme["yellow-500"]};
        border: 0;
        border-radius: 4px;
        padding: 0.2rem 0.3rem;
        cursor: pointer;
    }
`;

export const Modal = styled.form`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    background-color: ${props => props.theme["gray-100"]};
    padding: 2rem 3rem;
    border-radius: 6px;
    width: 50%;

    svg {
        position: absolute;
        right: 1rem;
        top: 1rem;
        cursor: pointer;
    }

    button[type="submit"] {
        padding: 0.875rem 1rem;
        background-color: ${props => props.theme["yellow-500"]};
        border: 0;
        margin-top: 1rem;
        border-radius: 4px;
        font-weight: bold;
        cursor: pointer;

        &:disabled {
            pointer-events: none;
            opacity: 0.6;
            cursor: disabled;
        }
    }
`

export const ModalOverlay = styled.div`
    inset: 0;
    background-color: ${props => props.theme["gray-800"]};
    position: fixed;
    width: 100vw;
    height: 100vh;
    opacity: 0.6;
`

export const InputsArea = styled.div`
    display: flex;
    flex-direction: column;


`

export const MoedasContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 0.5rem;
    margin: 1rem 0 ;

    label {
        font-weight: bold;
    }

    input {
        padding: 0.5rem 1rem;
        border-radius: 4px;
        border: 1px solid ${props => props.theme["gray-700"]};
    }
`

export const TextAreaContainer = styled.div`
    display: flex;
    flex-direction: column;
    gap: 0.5rem;

    label {
        font-weight: bold;
    }

    textarea {
        resize: none;
        border: 1px solid ${props => props.theme["gray-800"]};
        padding: 0.5rem 1rem;
        border-radius: 4px;
    }
`