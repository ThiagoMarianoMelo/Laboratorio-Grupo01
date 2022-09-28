import { BrowserRouter, Route, Routes } from "react-router-dom";
import { LoginScreen } from "../screens/login/Login.screen";
import { MainScreen } from "../screens/main/Main.screen";
import { AutomoveisScreen } from "../screens/automoveis/Automoveis.screen";

export const Paths = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/">
          <Route index element={<MainScreen />} />
          <Route path="login" element={<LoginScreen />} />
          <Route path="automoveis">
            <Route index element={<AutomoveisScreen />} />
          </Route>
          {/* <Route path="register" element={<RegisterScreen />} />  */}
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
