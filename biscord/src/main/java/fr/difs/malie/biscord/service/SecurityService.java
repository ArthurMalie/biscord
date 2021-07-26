package fr.difs.malie.biscord.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
