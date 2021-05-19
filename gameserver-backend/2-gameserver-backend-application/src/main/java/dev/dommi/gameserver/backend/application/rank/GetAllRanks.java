package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.Collection;

public class GetAllRanks {
    private final RankRepository repository;

    public GetAllRanks(RankRepository repository) {
        this.repository = repository;
    }

    public Collection<RankVO> getAll() {
        return repository.getAllRanks();
    }

}
