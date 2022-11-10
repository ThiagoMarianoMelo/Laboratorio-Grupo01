import styled from "styled-components";

export const LoginContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
`;

export const LoginContent = styled.main`
    display: flex;
    flex-direction: column;
    padding: 2rem;
    border-radius: 6px;
    background: ${props => props.theme["yellow-500"]};
    gap: 1.5rem;
`;

export const InputFieldsContainer = styled.form`
    display: flex;
    flex-direction: column;
    gap: 1.5rem;

    button[type="submit"] {
        margin-top: 1rem;
        border-radius: 6px;
        background: ${props => props.theme["purple-500"]};
        color: ${props => props.theme.white};
        font-weight: bold;
        transition: background 0.2s;
        padding: 0.5rem 1rem;
        border: 0;
        cursor: pointer;
    }
`;

export const InputField = styled.div`
    display: flex;
    flex-direction: column;
    gap: 0.25rem;

    label {
        font-weight: bold;
    }

    input {
        background: ${props => props.theme["gray-200"]};
        border-radius: 6px;
        padding: 0.5rem 1rem;
        border: 1px solid ${props => props.theme["gray-600"]};
    }
`