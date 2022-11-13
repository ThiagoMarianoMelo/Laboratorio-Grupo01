import { useContext, useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import { UserContext } from './context/UserContext';
import { DefaultLayout } from './layouts/DefaultLayout';
import { Home } from './pages/Home';
import { Login } from './pages/Login';
import { Register } from './pages/Register';
import { RegisterVantagem } from './pages/RegisterVantagem';

export function Router() {
    return (
        <Routes>
            <Route path="/" element={<DefaultLayout />}>
                <Route path="/" element={<Home />}/>
                <Route path="/login" element={<Login />}/>
                <Route path="/register" element={<Register />}/>
                <Route path="/vantagens/cadastrar" element={<RegisterVantagem />}/>
            </Route>
        </Routes>
    );
}