import { axios } from "../config/axios";
import { IEnviarMoedasRequest } from "../pages/EnviarMoedas";

class ProfessorRepository {
    public async EnviarMoedas(request: IEnviarMoedasRequest): Promise<number | null> {
        const url = '/enviar-moedas';

        try {
            const response = await axios.post(url, request);
            return response.data;

        }
        catch(err) {
            return null;            
        }
    }

}

const instance = new ProfessorRepository();
export { instance as ProfessorRepository };