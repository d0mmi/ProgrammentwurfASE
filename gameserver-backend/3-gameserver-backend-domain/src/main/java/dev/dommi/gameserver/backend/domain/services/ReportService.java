package dev.dommi.gameserver.backend.domain.services;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;

public interface ReportService {
    void report(UserRankAggregate creator, UserRankAggregate reported, String reason, int typeId);
}
