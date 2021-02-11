package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

import java.sql.SQLException;

public class ReportEntity {
    private int id;
    private UserEntity creator;
    private UserEntity reported;
    private String reason;
    private ReportTypeVO type;
    private boolean open;

    public ReportEntity(int id, UserEntity creator, UserEntity reported, String reason, ReportTypeVO type, boolean open) {
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

    public UserEntity getCreator() {
        return creator;
    }

    public UserEntity getReported() {
        return reported;
    }

    public String getReason() {
        return reason;
    }

    public ReportTypeVO getType() {
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
