import { APIManager } from "./APIManager";

export type LoginResponse = {

    token: string;

};
export type User = {

    id: number;
    name: string;
    email: string;
    level: number;


};

export class UserApi {

    public static async register(name: string, email: string, pw: string) {
        await APIManager.post("register", {
            "name": name,
            "email": email,
            "pw": pw
        }, false);
    }

    public static async login(email: string, pw: string): Promise<LoginResponse> {
        var login = await APIManager.post("login", {
            "email": email,
            "pw": pw
        }, false) as LoginResponse;
        return login;
    }

    public static async getAll(): Promise<User[]> {
        var login = await APIManager.get("users") as User[];
        return login;
    }
    public static async getById(id: number): Promise<User> {
        var login = await APIManager.get("users/" + id) as User;
        return login;
    }
    public static async deleteById(id: number) {
        await APIManager.delete("users/" + id);
    }
    public static async updateById(id: number, name: string, email: string) {
        await APIManager.patch("users/" + id, {
            "name": name,
            "email": email
        });
    }

}