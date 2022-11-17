import { axios } from "../config/axios";
import { TransacaoModel } from "../models/TransacaoModel";

class TransacoesRepository {
    public async ObterTransacoes(idUsuario: number): Promise<TransacaoModel[] | null> {
        const url = `/comprar-vantagem?idUsuario=${idUsuario}`;

        const response = await axios.get(url);

        if (response.status === 500) {
            return null;
        }

        return response.data;
    }
}

const instance = new TransacoesRepository();
export { instance as TransacoesRepository };