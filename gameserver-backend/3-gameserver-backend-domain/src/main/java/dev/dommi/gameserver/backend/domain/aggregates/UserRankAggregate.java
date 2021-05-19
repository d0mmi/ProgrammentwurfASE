package dev.dommi.gameserver.backend.domain.aggregates;

import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

public class UserRankAggregate {

    private final UserEntity userEntity;
    private RankVO rankVO;

    public UserRankAggregate(UserEntity userEntity, RankVO rankVO) {
        this.userEntity = userEntity;
        this.rankVO = rankVO;
    }

    public int getUserId() {
        return userEntity.getId();
    }

    public int getRankId() {
        return rankVO.getId();
    }

    public String getEmail() {
        return userEntity.getEmail();
    }

    public String getUserName() {
        return userEntity.getName();
    }

    public String getRankName() {
        return rankVO.getName();
    }

    public int getRankLevel() {
        return rankVO.getLevel();
    }

    public boolean modifyUser(String name, String email, String pw, UserRepository userRepository) {
        return userEntity.modify(name, email, pw, userRepository);
    }

    public boolean reportUser(int reportedUserId, String reason, int reportTypeId, ReportRepository reportRepository) {
        return userEntity.reportUser(reportedUserId, reason, reportTypeId, reportRepository);
    }

    public boolean grantRank(RankType rank, RankRepository rankRepository) {
        return grantRank(rankRepository.getRankIdFrom(rank.value), rankRepository);
    }

    public boolean grantRank(int rankId, RankRepository rankRepository) {
        if (rankRepository.revokeAllRanks(userEntity.getId())) {
            if (rankRepository.grantRank(userEntity.getId(), rankId)) {
                rankVO = rankRepository.getRankFrom(userEntity.getId());
                return true;
            }
        }
        return false;
    }

    public boolean revokeRank(RankRepository rankRepository) {
        if (rankVO.getName().equalsIgnoreCase(RankType.USER.value)) return false;
        int rankId = rankRepository.getRankIdFrom(RankType.USER.value);
        if (rankId > -1)
            return grantRank(rankId, rankRepository);
        return false;
    }

}
