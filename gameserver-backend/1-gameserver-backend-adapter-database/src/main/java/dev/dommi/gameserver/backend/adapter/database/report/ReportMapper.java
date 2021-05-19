package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.adapter.database.user.UserMapper;
import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

import java.util.ArrayList;
import java.util.Collection;

public class ReportMapper {


    public static ReportEntity getReportEntityFrom(Report report) {
        if (report == null) return null;
        return new ReportEntity(report.id, report.reason, report.open);
    }

    public static Collection<ReportEntity> getReportEntityCollectionFrom(Collection<Report> reports) {
        Collection<ReportEntity> entities = new ArrayList<>();
        for (Report report : reports) {
            if (report != null) {
                entities.add(getReportEntityFrom(report));
            }
        }
        return entities;
    }

    public static ReportAggregate getReportAggregateFrom(Report report, ReportType type, User creator, User reported) {
        if (report == null) return null;
        return new ReportAggregate(getReportEntityFrom(report), UserMapper.getUserEntityFrom(creator), UserMapper.getUserEntityFrom(reported), getReportTypeVOFrom(type));
    }


    public static ReportTypeVO getReportTypeVOFrom(ReportType type) {
        if (type == null) return null;
        return new ReportTypeVO(type.id, type.name);
    }

    public static Collection<ReportTypeVO> getReportTypeVOCollectionFrom(Collection<ReportType> types) {
        Collection<ReportTypeVO> vos = new ArrayList<>();
        for (ReportType type : types) {
            if (type != null) {
                vos.add(getReportTypeVOFrom(type));
            }
        }
        return vos;
    }

}
