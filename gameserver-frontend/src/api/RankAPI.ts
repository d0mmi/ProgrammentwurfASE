import { APIManager, Error } from "./APIManager";

export type Rank = {
    id: number;
    name: string;
    level: number;

};

export class RankAPI {

    public static async getRanks(): Promise<Rank[] | Error> {
        return await APIManager.get("admin/ranks");
    }

    public static grantRankTo(userId: number, rank: String) {
        APIManager.post("admin/ranks/grant", { userId: userId, rank: rank });
    }

    public static revokeRankFrom(userId: number) {
        APIManager.post("admin/ranks/revoke", { userId: userId });
    }

}