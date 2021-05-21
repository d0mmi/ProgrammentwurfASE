package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.services.CredentialService;


public class UserEntity {

    private final int id;
    private String name;
    private String email;

    public UserEntity(int id, String name, String email) {
        if (id < 0 && id != -1) throw new IllegalArgumentException("id must be >= 0 or -1 to count as not set");
        this.id = id;
        CredentialService credentialService = new CredentialService();
        if (!credentialService.isNameValid(name)) throw new IllegalArgumentException("name is invalid");
        this.name = name;
        if (!credentialService.isEmailValid(email)) throw new IllegalArgumentException("email is invalid");
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

    public boolean modify(String name, String email) {
        CredentialService credentialService = new CredentialService();
        if (credentialService.credentialsValidOrNull(name, email, null)) {
            this.name = name;
            this.email = email;
            return true;
        }
        return false;
    }

    public UserEntity copy() {
        return new UserEntity(id, name, email);
    }

}
