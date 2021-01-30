import { APIManager, Error } from "./APIManager";

export type Ban = {

    id: number;
    userId: number;
    bannedById: number;
    reason: string;
    until: string;
    active: boolean;

};

export class BanAPI {

    public static async getBans(): Promise<Ban[] | Error> {
        return await APIManager.get("admin/bans");
    }

    public static ban(userId: number, reason: String, untilDate: String) {
        APIManager.post("admin/bans", { userId: userId, reason: reason, until: untilDate });
    }

}