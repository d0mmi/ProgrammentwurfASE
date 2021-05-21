package dev.dommi.gameserver.backend.domain.aggregates;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
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

    public boolean modifyUser(String name, String email) {
        return userEntity.modify(name, email);
    }

    public void grantRank(RankVO rank) {
        this.rankVO = rank;
    }

}
