import { APIManager, Error } from "./APIManager";
import { User } from "./UserApi";

export type Report = {

    id: number;
    creator: User;
    reported: User;
    reason: string;
    type: ReportType;
    open: boolean;

};
export type ReportType = {
    id: number;
    name: string;

};

export class ReportAPI {

    public static async getReportTypes(): Promise<ReportType[] | Error> {
        return await APIManager.get("admin/reports/types");
    }
    public static async getReports(): Promise<Report[] | Error> {
        return await APIManager.get("admin/reports");
    }
    public static report(reportedUserId: number, reason: String, reportTypeId: number) {
        APIManager.post("report", { reportedUserId: reportedUserId, reason: reason, reportTypeId: reportTypeId });
    }
    public static updateReportStatus(reportId: number, status: boolean) {
        APIManager.post("admin/reports/" + reportId, { status: status });
    }

}