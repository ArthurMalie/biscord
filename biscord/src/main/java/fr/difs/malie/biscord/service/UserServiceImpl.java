package fr.difs.malie.biscord.service;

import fr.difs.malie.biscord.data.UserDAO;
import fr.difs.malie.biscord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Sauvegarde un utilisateur dans le repository userDAO.
     * @param user l'utilisateur à sauvegarder
     */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    /**
     * Trouve un utilisateur dans le repository userDAO en fonction de son username
     * @param username de l'utilisateur à récupérer
     * @return l'utilisateur à récupérer.
     */
    @Override
    public User findByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }
}
