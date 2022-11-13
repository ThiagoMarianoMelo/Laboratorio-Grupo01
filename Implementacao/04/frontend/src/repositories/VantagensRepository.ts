import { HttpStatusCode } from "axios";
import { axios } from "../config/axios";
import { VantagensModel } from "../models/VantagensModel";
import { IRegisterVantagemRequest } from "../pages/RegisterVantagem";

class VantagensRepositorio {
    public async ListVantagens(): Promise<VantagensModel[]> {
        const url = '/listar-vantagens';

        const response = await axios.get(url);
        return response.data ?? [];
    }

    public async CadastrarVantagem(request: IRegisterVantagemRequest): Promise<number | null> {
        const url = '/cadastrar-vantagem';

        const response = await axios.post(url, request);
        return response.data ?? [];
    }
}

const instance = new VantagensRepositorio();
export { instance as VantagensRepositorio };