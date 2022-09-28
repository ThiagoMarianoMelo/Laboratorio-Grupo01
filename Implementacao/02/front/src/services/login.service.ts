import axios from "axios";
import { BASE_URL } from "../config/constants";

export const loginUser = async (email: string, password: string) => {
  const url = `${BASE_URL}/login`;
  const body = {
    email,
    senha: password,
  };

  const result = {
    containsError: false,
    error: "",
  };
  try {
    const { data } = await axios.post(url, body);
    const userDataFromResponse = data.data;

    const userData = {
      ...userDataFromResponse.user_data,
      token: userDataFromResponse.token,
      shoppingCart: [],
    };
    window.sessionStorage.setItem("user", JSON.stringify(userData));
  } catch (error) {
    console.log(error);
    result.containsError = true;
    result.error = "Usuário não encontrado";
  } finally {
    return result;
  }
};
