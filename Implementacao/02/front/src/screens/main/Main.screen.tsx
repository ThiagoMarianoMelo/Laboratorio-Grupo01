import { BasicButton } from "../../components/buttons/BasicButton.component";
import { useNavigate } from "react-router-dom";

export const MainScreen = () => {
  const navigate = useNavigate();
  const navigateToScreen = (isLogin: boolean) => {
    if (isLogin) {
      navigate("/login");
    } else {
      navigate("/register");
    }
  };
  return (
    <>
      <BasicButton text="Login" onClick={() => navigateToScreen(true)} />
      <BasicButton text="Registrar" onClick={() => navigateToScreen(true)} />
    </>
  );
};
