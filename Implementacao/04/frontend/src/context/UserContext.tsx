import { UserModel } from '../models/UserModel'
import React, { ReactNode, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';

export interface IUserContext {
    saveUser: (user: UserModel) => void;
    verifyIfUserIsLoggedIn: () => void;
    user: UserModel | null;
    logoutUser: () => void;
}

interface IUserProvider {
    children: ReactNode;
}

export const UserContext = React.createContext<IUserContext>({} as IUserContext)

export const UserProvider = ({children}: IUserProvider) => {
    const [user, setUser] = useState<UserModel | null>(null)
    const navigate = useNavigate();

    useEffect(() => {
        verifyIfUserIsLoggedIn();
    }, [user]);

    function verifyIfUserIsLoggedIn() {
        const userFound = localStorage.getItem('@sistema-moeda-estudantil:user')

        if (userFound) {
            setUser(JSON.parse(userFound))
        }
        else {
            navigate('/login');
        }
    }

    function logoutUser() {
        localStorage.removeItem('@sistema-moeda-estudantil:user')
        setUser(null)
    }

    function saveUser(user: UserModel) {
        const userJSON = JSON.stringify(user)
        setUser(user)
        localStorage.setItem('@sistema-moeda-estudantil:user', userJSON)
    }

    const store: IUserContext = {
        saveUser,
        verifyIfUserIsLoggedIn,
        user,
        logoutUser
    }

    return <UserContext.Provider value={store}>{children}</UserContext.Provider>
}
