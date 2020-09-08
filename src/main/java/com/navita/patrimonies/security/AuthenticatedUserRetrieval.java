package com.navita.patrimonies.security;

import com.navita.patrimonies.entities.User;
import com.navita.patrimonies.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserRetrieval {

    @Autowired
    private UserRepository userRepository;

    public User retrieve() {
        String userId = getUserId();
        return userRepository.findByLogin(userId).orElse(null);
    }

    private String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
