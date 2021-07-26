package fr.difs.malie.biscord.service;

import fr.difs.malie.biscord.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
