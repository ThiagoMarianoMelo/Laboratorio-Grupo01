import axios from "axios";
import { BASE_URL } from "../config/constants";

export const getAutomoveis = async (tipo?: string) => {
  const path = `${BASE_URL}/automovel`;
  let query = "";
  if (tipo) {
    query = `?tipoDeObtencaoDeAutomovel=${tipo}`;
  }
  const url = `${path}${query}`;
  const result = await axios.get(url);
  return result.data
};
