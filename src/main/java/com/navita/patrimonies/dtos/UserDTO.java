package com.navita.patrimonies.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.navita.patrimonies.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties
public class UserDTO {

    private final String name;

    private final String login;

    private final String password;

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .build();
    }

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
