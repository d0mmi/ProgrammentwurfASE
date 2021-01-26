package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.sql.SQLException;
import java.util.*;

public class BanRepositoryMock implements BanRepository {
    @Override
    public void create(int userId, int bannedById, String reason, Date until) throws SQLException {

    }

    @Override
    public void update(int id, String reason, Date until, boolean active) throws SQLException {

    }

    @Override
    public BanEntity findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Collection<BanEntity> findAll() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanEntity> findAllByActive(boolean active) throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanEntity> findAllByUser(int userId) throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanEntity> findAllByDate(Date date) throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Collection<BanEntity> findAllByUserAndDate(int userId, Date date) throws SQLException {
        return new ArrayList<>();
    }
}
