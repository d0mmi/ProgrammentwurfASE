package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.sql.SQLException;

public class ReportEntity {
    private int id;
    private int creator;
    private int reported;
    private String reason;
    private ReportTypeEntity type;
    private boolean open;

    public ReportEntity(int id, int creator, int reported, String reason, ReportTypeEntity type, boolean open) {
        this.id = id;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.type = type;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public int getCreator() {
        return creator;
    }

    public int getReported() {
        return reported;
    }

    public String getReason() {
        return reason;
    }

    public ReportTypeEntity getType() {
        return type;
    }

    public boolean isOpen() {
        return open;
    }

    public ReportEntity updateStatus(boolean status, ReportRepository reportRepository) throws SQLException {
        reportRepository.updateReportStatus(id, status);
        this.open = status;
        return this;
    }

}
