package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.CredentialService;


public class UserEntity {

    private int id;
    private String name;
    private String email;

    public UserEntity(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean modify(String name, String email, String pw, UserRepository userRepository) {
        CredentialService credentialService = new CredentialService();
        if (email == null || !credentialService.isEmailInUse(userRepository, email) && credentialService.credentialsValidOrNull(name, email, pw)) {
            userRepository.update(id, name, email, pw);
            this.name = name;
            this.email = email;
            return true;
        }
        return false;
    }

    public boolean reportUser(int reportedUserId, String reason, int reportTypeId, ReportRepository reportRepository){
        return reportRepository.reportUser(id,reportedUserId,reason,reportTypeId);
    }

}
