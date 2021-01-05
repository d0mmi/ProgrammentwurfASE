package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

public class UserEntity {
    public int id;
    public String name;
    public String email;
    public RankVO rank;

    public UserEntity(int id, String name, String email, RankVO rank) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rank = rank;
    }
}
