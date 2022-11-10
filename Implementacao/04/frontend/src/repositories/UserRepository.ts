import { UserModel } from "../models/UserModel";
import { ILoginUserRequest } from "../pages/Login";

class UserRepository {
    public async Login(request: ILoginUserRequest): Promise<UserModel | null> {
        
    }
}

const instance = new UserRepository();
export { instance as UserRepository };