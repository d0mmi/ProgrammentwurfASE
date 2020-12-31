import fetch from 'node-fetch';

export class APIManager {

    private static token: string;

    public static async get(query: string) {
        return await this.sendRequest(query);
    }
    public static async post(query: string, body: any, requiresToken: boolean = true) {
        return await this.sendRequest(query, "post", body, requiresToken);
    }
    public static async put(query: string, body: any) {
        return await this.sendRequest(query, "put", body);
    }
    public static async patch(query: string, body: any) {
        return await this.sendRequest(query, "patch", body);
    }
    public static async delete(query: string) {
        return await this.sendRequest(query, "delete");
    }

    private static async sendRequest(query: string, method: string = "get", body: any = undefined, requiresToken: boolean = true) {
        console.log(window.location.host + "/api/");
        var headers: any = (body === null) ? {} : { 'Content-Type': 'application/json' }
        if (this.token !== undefined && this.token !== null && this.token.length > 0) {
            headers['Authorization'] = 'Bearer ' + this.token;
        } else if (requiresToken) {
            throw new Error("No token provided!");
        }
        var response = await fetch(window.location.host + "/api/" + query, { method: method, body: (body === undefined) ? undefined : JSON.stringify(body), headers: headers });
        var body = await response.json();
        return body;
    }
}