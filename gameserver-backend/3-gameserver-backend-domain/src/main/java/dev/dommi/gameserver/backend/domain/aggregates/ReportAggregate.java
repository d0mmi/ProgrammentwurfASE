package dev.dommi.gameserver.backend.domain.aggregates;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
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

    public int getCreatorId() {
        return creator.getId();
    }

    public int getReportedId() {
        return reported.getId();
    }

    public String getCreatorEmail() {
        return creator.getEmail();
    }

    public String getReportedEmail() {
        return reported.getEmail();
    }

    public String getCreatorName() {
        return creator.getName();
    }

    public String getReportedName() {
        return reported.getName();
    }

    public int getReportTypeId() {
        return reportTypeVO.getId();
    }

    public String getReportTypeName() {
        return reportTypeVO.getName();
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

    public boolean updateStatus(boolean status, ReportRepository repository) {
        return reportEntity.updateStatus(status, repository);
    }


}
