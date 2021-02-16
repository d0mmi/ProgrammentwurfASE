import { APIManager, Error } from "./APIManager";

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

    public static async register(name: string, email: string, pw: string): Promise<any | Error> {
        return await APIManager.post("register", {
            "name": name,
            "email": email,
            "pw": pw
        }, false);
    }

    public static async login(email: string, pw: string): Promise<LoginResponse | Error> {
        return await APIManager.post("login", {
            "email": email,
            "pw": pw
        }, false);;
    }

    public static async getAll(): Promise<User[] | Error> {
        return await APIManager.get("users");
    }
    public static async get(): Promise<User | Error> {
        return await APIManager.get("user");
    }
    public static async getById(id: number): Promise<User | Error> {
        return await APIManager.get("users/" + id);
    }
    public static async deleteById(id: number) {
        await APIManager.delete("users/" + id);
    }
    public static async updateById(id: number, name: string | null, email: string | null) {
        await APIManager.post("users/" + id, {
            "name": name,
            "email": email
        });
    }

}