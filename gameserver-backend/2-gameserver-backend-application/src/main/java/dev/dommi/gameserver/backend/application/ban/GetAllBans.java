package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.util.Collection;
import java.util.Date;

public class GetAllBans {

    public GetAllBans(BanRepository repository) {
        this.repository = repository;
    }

    private final BanRepository repository;

    public BanAggregate getOne(int id) {
        return repository.findById(id);
    }

    public Collection<BanAggregate> getAll() {
        return repository.findAll();
    }

    public Collection<BanAggregate> getAll(int userId) {
        return repository.findAllByUser(userId);
    }

    public Collection<BanAggregate> getAll(boolean active) {
        return repository.findAllByActive(active);
    }

    public Collection<BanAggregate> getAll(Date date) {
        return repository.findAllByDate(date);
    }

    public Collection<BanAggregate> getAll(int userId, Date date) {
        return repository.findAllByUserAndDate(userId, date);
    }
}
