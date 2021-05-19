package dev.dommi.gameserver.backend.domain.services;

import dev.dommi.gameserver.backend.domain.repositories.UserRepository;


public class CredentialService {

    private static final String NAME_REGEX = "(?:[A-Z]|[a-z]|[0-9]|_){4,16}";
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    private static final String PW_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";


    public boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    public boolean isEmailValid(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public boolean isEmailInUse(UserRepository userRepository, String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean isPasswordValid(String password) {
        return password.matches(PW_REGEX);
    }

    public boolean credentialsValid(String name, String email, String password) {
        return isNameValid(name) && isEmailValid(email) && isPasswordValid(password);
    }

    public boolean credentialsValidOrNull(String name, String email, String password) {
        return (name == null || isNameValid(name)) && (email == null || isEmailValid(email)) && (password == null || isPasswordValid(password));
    }

}
