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
`;