import { HttpStatusCode } from "axios";
import { axios } from "../config/axios";
import { UserModel } from "../models/UserModel";
import { IBuyVantagemRequest } from "../pages/Home";
import { ILoginUserRequest } from "../pages/Login";
import { IRegisterUserRequest } from "../pages/Register";

class AlunoRepository {
    public async ComprarVantagem(request: IBuyVantagemRequest): Promise<UserModel | null> {
        const url = '/comprar-vantagem';

        const response = await axios.post(url, request);

        if (response.status === 500) {
            return null;
        }

        return response.data;
    }

    public async ObterAlunos(): Promise<UserModel[] | null> {
        const url = '/listar-alunos';

        const response = await axios.get(url);

        return response.data;
    }
}

const instance = new AlunoRepository();
export { instance as AlunoRepository };