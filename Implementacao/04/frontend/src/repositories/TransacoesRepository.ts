import { axios } from "../config/axios";
import { TransacaoModel } from "../models/TransacaoModel";

export interface ObterTransacoesResposta {
    saldoAtualUser: number;
    transacoes: TransacaoModel[];
}

class TransacoesRepository {
    public async ObterTransacoes(idUsuario: number): Promise<ObterTransacoesResposta | null> {
        const url = `/ListarTransacoesUsuario?idUsuario=${idUsuario}`;

        const response = await axios.get(url);

        return response.data;
    }
}

const instance = new TransacoesRepository();
export { instance as TransacoesRepository };