package dev.dommi.gameserver.backend.plugin.api.services.report;

public class ReportUserRequest {
    public int reportedUserId;
    public String reason;
    public String reportType;

    public ReportUserRequest() {
    }

    public ReportUserRequest(int reportedUserId, String reason, String reportType) {
        this.reportedUserId = reportedUserId;
        this.reason = reason;
        this.reportType = reportType;
    }
}
