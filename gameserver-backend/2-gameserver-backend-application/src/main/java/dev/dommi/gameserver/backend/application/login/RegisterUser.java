package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.entities.RankType;
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
        if (credentialService.isNameValid(name) && credentialService.isEmailValid(email) && !credentialService.isEmailInUse(userRepository, email) && credentialService.isPasswordValid(pw)) {
            userRepository.create(name, email, pw);
            UserRankAggregate user = userRepository.findByEmail(email);
            if (user != null) {
                return user.grantRank(RankType.USER, rankRepository);
            }
        }
        return false;
    }

}
