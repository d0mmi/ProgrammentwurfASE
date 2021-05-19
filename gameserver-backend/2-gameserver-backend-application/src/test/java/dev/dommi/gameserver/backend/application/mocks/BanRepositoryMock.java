package dev.dommi.gameserver.backend.application.mocks;

import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        return new BanAggregate(new BanEntity(id, "exampleReason", cal.getTime(), true), new UserEntity(0, "test1", "test@test.com"), new UserEntity(1, "test2", "test2@test.com"));
    }

    @Override
    public Collection<BanAggregate> findAll() {
        return Arrays.asList(findById(1));
    }

    @Override
    public Collection<BanAggregate> findAllByActive(boolean active) {
        return Arrays.asList(new BanAggregate(new BanEntity(0, "exampleReason", new Date(), true), new UserEntity(0, "test1", "test@test.com"), new UserEntity(1, "test2", "test2@test.com")));
    }

    @Override
    public Collection<BanAggregate> findAllByUser(int userId) {
        return Arrays.asList(new BanAggregate(new BanEntity(0, "exampleReason", new Date(), true), new UserEntity(userId, "test1", "test@test.com"), new UserEntity(1, "test2", "test2@test.com")));
    }

    @Override
    public Collection<BanAggregate> findAllByDate(Date date) {
        return Arrays.asList(new BanAggregate(new BanEntity(0, "exampleReason", date, true), new UserEntity(0, "test1", "test@test.com"), new UserEntity(1, "test2", "test2@test.com")));
    }

    @Override
    public Collection<BanAggregate> findAllByUserAndDate(int userId, Date date) {
        return Arrays.asList(new BanAggregate(new BanEntity(0, "exampleReason", date, true), new UserEntity(userId, "test1", "test@test.com"), new UserEntity(1, "test2", "test2@test.com")));
    }
}
