package fr.difs.malie.biscord.service;

import fr.difs.malie.biscord.data.UserDAO;
import fr.difs.malie.biscord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    /**
     * Charge les UserDetails d'un utilisateur en fonction de son username.
     * @see SecurityServiceImpl
     * @param username le username de l'utilisateur à charger
     * @return les UserDetails de l'utilisateur à charger
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.findUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user.isModerator()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("moderator"));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
