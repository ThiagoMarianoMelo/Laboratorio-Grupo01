import { useContext, useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import { UserContext } from './context/UserContext';
import { DefaultLayout } from './layouts/DefaultLayout';
import { Home } from './pages/Home';
import { Login } from './pages/Login';

export function Router() {
    const { verifyIfUserIsLoggedIn } = useContext(UserContext);
    
    useEffect(() => {
        verifyIfUserIsLoggedIn();
    }, []);

    return (
        <Routes>
            <Route path="/" element={<DefaultLayout />}>
                <Route path="/" element={<Home />}/>
                <Route path="/login" element={<Login />}/>
            </Route>
        </Routes>
    );
}