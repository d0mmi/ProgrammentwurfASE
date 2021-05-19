package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.util.Collection;
import java.util.Date;

public interface BanDatabaseController extends DatabaseController<Ban> {

    Collection<Ban> findAllByActive(boolean active);
    Collection<Ban> findAllByUser(int userId);
    Collection<Ban> findAllByDate(Date date);
    Collection<Ban> findAllByUserAndDate(int userId, Date date);

}
