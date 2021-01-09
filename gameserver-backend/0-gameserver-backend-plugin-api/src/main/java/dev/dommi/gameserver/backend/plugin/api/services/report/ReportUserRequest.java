package dev.dommi.gameserver.backend.plugin.api.services.report;

public class ReportUserRequest {
    public int creatorId;
    public int reportedUserId;
    public String reason;
    public int reportTypeId;

    public ReportUserRequest() {
    }

    public ReportUserRequest(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        this.creatorId = creatorId;
        this.reportedUserId = reportedUserId;
        this.reason = reason;
        this.reportTypeId = reportTypeId;
    }
}
