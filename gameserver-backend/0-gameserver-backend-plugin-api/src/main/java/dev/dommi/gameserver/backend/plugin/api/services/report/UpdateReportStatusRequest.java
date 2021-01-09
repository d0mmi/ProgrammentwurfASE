package dev.dommi.gameserver.backend.plugin.api.services.report;

public class UpdateReportStatusRequest {
    public boolean status;

    public UpdateReportStatusRequest() {
    }

    public UpdateReportStatusRequest(boolean status) {
        this.status = status;
    }
}
