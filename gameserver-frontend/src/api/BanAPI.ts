import { APIManager, Error } from "./APIManager";
import { User } from "./UserApi";

export type Ban = {

    id: number;
    user: User;
    bannedBy: User;
    reason: string;
    until: string;
    active: boolean;

};

export class BanAPI {

    public static async getBans(): Promise<Ban[] | Error> {
        return await APIManager.get("admin/bans");
    }

    public static ban(userId: number, reason: string, untilDate: string) {
        APIManager.post("admin/bans", { userId: userId, reason: reason, until: untilDate });
    }
    public static updateBanStatus(banId: number, reason: string | null, untilDate: string | null, active: boolean) {
        APIManager.post("admin/bans/" + banId, { reason: reason, until: untilDate, active });
    }

}