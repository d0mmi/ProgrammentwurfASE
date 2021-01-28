import fetch from 'node-fetch';


export type Error = {
    code: string,
    message: string
}

export class APIManager {

    public static token: string;

    public static async get(query: string): Promise<any | Error> {
        return await this.sendRequest(query);
    }
    public static async post(query: string, body: any, requiresToken: boolean = true): Promise<any | Error> {
        return await this.sendRequest(query, "post", body, requiresToken);
    }
    public static async put(query: string, body: any): Promise<any | Error> {
        return await this.sendRequest(query, "put", body);
    }
    public static async patch(query: string, body: any): Promise<any | Error> {
        return await this.sendRequest(query, "patch", body);
    }
    public static async delete(query: string): Promise<any | Error> {
        return await this.sendRequest(query, "delete");
    }

    private static async sendRequest(query: string, method: string = "get", body: any = undefined, requiresToken: boolean = true): Promise<any | Error> {
        console.log(window.location.host + "/api/" + query);
        var headers: any = (body === null) ? {} : { 'Content-Type': 'application/json' }
        if (this.token !== undefined && this.token !== null && this.token.length > 0) {
            headers['Authorization'] = 'Bearer ' + this.token;
        } else if (requiresToken) {
            throw new Error("No token provided!");
        }
        var response = await fetch(window.location.protocol + "//" + window.location.host + "/api/" + query, { method: method, body: (body === undefined) ? undefined : JSON.stringify(body), headers: headers });
        if (response.status === 200 || response.status === 201) return await response.json();

        return {
            code: response.status,
            message: response.statusText
        };
    }
}