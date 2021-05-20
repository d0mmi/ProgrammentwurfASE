package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;

import java.util.Collection;
import java.util.Date;

public interface BanRepository {

    boolean create(int userId, int bannedById, String reason, Date until);

    boolean update(BanAggregate ban);

    BanAggregate findById(int id);

    Collection<BanAggregate> findAll();

    Collection<BanAggregate> findAllByActive(boolean active);

    Collection<BanAggregate> findAllByUser(int userId);

    Collection<BanAggregate> findAllByDate(Date date);

    Collection<BanAggregate> findAllByUserAndDate(int userId, Date date);

}
