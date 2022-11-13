import { HttpStatusCode } from "axios";
import { axios } from "../config/axios";
import { VantagensModel } from "../models/VantagensModel";

class VantagensRepositorio {
    public async ListVantagens(): Promise<VantagensModel[]> {
        const url = '/listar-vantagens';

        const response = await axios.get(url);
        return response.data ?? [];
    }
}

const instance = new VantagensRepositorio();
export { instance as VantagensRepositorio };