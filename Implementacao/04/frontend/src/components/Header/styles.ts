import styled from "styled-components";

export const HeaderContainer = styled.header`
    width: 100%;
    background: ${props => props.theme["yellow-500"]};
    padding: 1rem;
`;

export const HeaderContent = styled.div`
    display: flex;
    justify-content: space-between;
    max-width: 1120px;
    margin: 0 auto;
    align-items: center;
    
    h3 {
        letter-spacing: 0.11rem;
        color: ${props => props.theme["gray-800"]};
        cursor: pointer;
    } 

    button {
        background: transparent;
        border: 0;
        cursor: pointer;

        svg {
            color: ${props => props.theme["gray-800"]};
        }
    }
`;

export const UserActionsAndInfo = styled.div`
    display: flex;
    align-items: center;
    gap: 3rem;
`;

export const UserProfile = styled.div`
    display: flex;
    flex-direction: column;
    gap: 0.25rem;
    text-align: right;

    strong {
        text-transform: capitalize;
    }
`;

export const Actions = styled.div`
    display: flex;
    align-items: center;
    gap: 1rem;
`