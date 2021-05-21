package dev.dommi.gameserver.backend.application.auth;

import dev.dommi.gameserver.backend.application.rank.RankServiceImpl;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.CredentialService;

public class RegisterUser {

    private final UserRepository userRepository;
    private final RankRepository rankRepository;

    public RegisterUser(UserRepository userRepository, RankRepository rankRepository) {
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
    }

    public boolean registerUser(String name, String email, String pw) {
        CredentialService credentialService = new CredentialService();
        if (credentialService.isNameValid(name) && credentialService.isEmailValid(email) && userRepository.findByEmail(email) == null && credentialService.isPasswordValid(pw)) {
            userRepository.create(name, email, pw);
            UserRankAggregate user = userRepository.findByEmail(email);
            if (user != null) {
                new RankServiceImpl(rankRepository, userRepository).grantRankTo(user, RankType.USER);
                return true;
            }
        }
        return false;
    }

}
