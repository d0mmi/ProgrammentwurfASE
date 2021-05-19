package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

public class ReportEntity {
    private int id;
    private String reason;
    private boolean open;

    public ReportEntity(int id, String reason, boolean open) {
        this.id = id;
        this.reason = reason;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean updateStatus(boolean status, ReportRepository reportRepository) {
        if(reportRepository.updateReportStatus(id, status)){
            this.open = status;
            return true;
        }
        return false;
    }

}
