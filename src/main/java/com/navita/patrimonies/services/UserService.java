package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.UserDTO;
import com.navita.patrimonies.entities.User;
import com.navita.patrimonies.respositories.UserRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User persist(UserDTO userDTO) {
        var user = UserDTO.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .map(UserDTO::toDto)
                .orElseThrow(() -> new NotFoundException("No User found for id " + id));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
