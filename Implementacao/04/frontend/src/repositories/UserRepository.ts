import { HttpStatusCode } from "axios";
import { axios } from "../config/axios";
import { UserModel } from "../models/UserModel";
import { ILoginUserRequest } from "../pages/Login";
import { IRegisterUserRequest } from "../pages/Register";

class UserRepository {
    public async Login(request: ILoginUserRequest): Promise<UserModel | null> {
        const url = '/login';

        const response = await axios.post(url, request);
        return response.data;
    }

    public async Register(request: IRegisterUserRequest): Promise<UserModel | null> {
        const url = '/cadastrar';

        const response = await axios.post(url, request);
        return response.data;
    }
}

const instance = new UserRepository();
export { instance as UserRepository };