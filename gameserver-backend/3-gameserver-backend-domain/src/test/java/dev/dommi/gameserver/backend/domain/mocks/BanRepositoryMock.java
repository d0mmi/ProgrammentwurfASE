package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.sql.SQLException;
import java.util.*;

public class BanRepositoryMock implements BanRepository {
    @Override
    public boolean create(int userId, int bannedById, String reason, Date until) {
    return true;
    }

    @Override
    public boolean update(int id, String reason, Date until, boolean active) {
        return true;
    }

    @Override
    public BanAggregate findById(int id) {
        return null;
    }

    @Override
    public Collection<BanAggregate> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanAggregate> findAllByActive(boolean active) {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanAggregate> findAllByUser(int userId) {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanAggregate> findAllByDate(Date date) {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanAggregate> findAllByUserAndDate(int userId, Date date) {
        return new ArrayList<>();
    }
}
