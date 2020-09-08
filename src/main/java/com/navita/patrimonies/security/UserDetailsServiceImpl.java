package com.navita.patrimonies.security;

import com.navita.patrimonies.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.navita.patrimonies.entities.User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));

        User userload = new User(user.getLogin(), (user.getPassword()), emptyList());
        return userload;
    }

}
