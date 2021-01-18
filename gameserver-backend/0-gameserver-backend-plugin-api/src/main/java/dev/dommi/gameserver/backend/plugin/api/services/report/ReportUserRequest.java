package dev.dommi.gameserver.backend.plugin.api.services.report;

public class ReportUserRequest {
    public int reportedUserId;
    public String reason;
    public int reportTypeId;

    public ReportUserRequest() {
    }

    public ReportUserRequest(int reportedUserId, String reason, int reportTypeId) {
        this.reportedUserId = reportedUserId;
        this.reason = reason;
        this.reportTypeId = reportTypeId;
    }
}
