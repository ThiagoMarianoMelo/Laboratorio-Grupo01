import styled from "styled-components";

export const RegisterContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
`;

export const RegisterContent = styled.main`
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

    button {
        border-radius: 6px;
        font-weight: bold;
        transition: background 0.2s;
        padding: 0.5rem 1rem;
        border: 0;
        cursor: pointer;
    }

    button[type="button"] {
        background: ${props => props.theme["gray-300"]};
        color: ${props => props.theme["gray-800"]};
    }

    button[type="submit"] {
        margin-top: 1rem;
        background: ${props => props.theme["purple-500"]};
        color: ${props => props.theme.white};
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