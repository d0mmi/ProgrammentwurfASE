package dev.dommi.gameserver.backend.domain.aggregates;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

public class ReportAggregate {

    private final ReportEntity reportEntity;
    private final UserEntity creator;
    private final UserEntity reported;
    private final ReportTypeVO reportTypeVO;


    public ReportAggregate(ReportEntity reportEntity, UserEntity creator, UserEntity reported, ReportTypeVO reportTypeVO) {
        this.reportEntity = reportEntity;
        this.creator = creator;
        this.reported = reported;
        this.reportTypeVO = reportTypeVO;
    }

    public UserEntity getCreator() {
        return creator.copy();
    }

    public UserEntity getReported() {
        return reported.copy();
    }

    public ReportTypeVO getReportType() {
        return reportTypeVO.copy();
    }


    public int getReportId() {
        return reportEntity.getId();
    }

    public String getReason() {
        return reportEntity.getReason();
    }

    public boolean isOpen() {
        return reportEntity.isOpen();
    }

    public boolean updateStatus(boolean status) {
        return reportEntity.updateStatus(status);
    }


}
