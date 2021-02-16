package dev.dommi.gameserver.backend.application.mocks;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class BanRepositoryMock implements BanRepository {
    @Override
    public void create(int userId, int bannedById, String reason, Date until) throws SQLException {

    }

    @Override
    public void update(int id, String reason, Date until, boolean active) throws SQLException {

    }

    @Override
    public BanEntity findById(int id) throws SQLException {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        return new BanEntity(id, new UserEntity(0,"test1","test@test.com",new RankVO(1,"test",0)), new UserEntity(1,"test2","test2@test.com",new RankVO(1,"test",0)), "exampleReason", cal.getTime(), true);
    }

    @Override
    public Collection<BanEntity> findAll() throws SQLException {
        return Arrays.asList(findById(1));
    }

    @Override
    public Collection<BanEntity> findAllByActive(boolean active) throws SQLException {
        return Arrays.asList(new BanEntity(1, new UserEntity(0,"test1","test@test.com",new RankVO(1,"test",0)), new UserEntity(1,"test2","test2@test.com",new RankVO(1,"test",0)), "exampleReason", new Date(), active));
    }

    @Override
    public Collection<BanEntity> findAllByUser(int userId) throws SQLException {
        return Arrays.asList(new BanEntity(1, new UserEntity(userId,"test1","test@test.com",new RankVO(1,"test",0)), new UserEntity(1,"test2","test2@test.com",new RankVO(1,"test",0)), "exampleReason", new Date(), true));
    }

    @Override
    public Collection<BanEntity> findAllByDate(Date date) throws SQLException {
        return Arrays.asList(new BanEntity(1, new UserEntity(0,"test1","test@test.com",new RankVO(1,"test",0)), new UserEntity(1,"test2","test2@test.com",new RankVO(1,"test",0)), "exampleReason", date, true));
    }

    @Override
    public Collection<BanEntity> findAllByUserAndDate(int userId, Date date) throws SQLException {
        return Arrays.asList(new BanEntity(1, new UserEntity(userId,"test1","test@test.com",new RankVO(1,"test",0)), new UserEntity(1,"test2","test2@test.com",new RankVO(1,"test",0)), "exampleReason", date, true));
    }
}
