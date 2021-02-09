import { APIManager, Error } from "./APIManager";

export type Report = {

    id: number;
    creator: number;
    reported: number;
    reason: string;
    type: ReportType;
    open: boolean;

};
export type ReportType = {

    name: string;

};

export class ReportAPI {

    public static async getReportTypes(): Promise<ReportType[] | Error> {
        return await APIManager.get("admin/reports/types");
    }
    public static async getReports(): Promise<Report[] | Error> {
        return await APIManager.get("admin/reports");
    }
    public static report(reportedUserId: number, reason: String, reportType: String) {
        APIManager.post("report", { reportedUserId: reportedUserId, reason: reason, reportType: reportType });
    }
    public static updateReportStatus(reportId: number, status: boolean) {
        APIManager.post("admin/reports/" + reportId, { status: status });
    }

}