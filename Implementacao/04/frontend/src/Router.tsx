import { Routes, Route } from 'react-router-dom';
import { DefaultLayout } from './layouts/DefaultLayout';
import { Login } from './pages/Login';

export function Router() {
    return (
        <Routes>
            <Route path="/" element={<DefaultLayout />}>
                {/* <Route path="/" element={<Home />}/> */}
                <Route path="/login" element={<Login />}/>
            </Route>
        </Routes>
    );
}