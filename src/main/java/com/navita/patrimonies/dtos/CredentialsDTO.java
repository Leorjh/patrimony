package com.navita.patrimonies.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CredentialsDTO {
    private final String login;
    private final String password;
}
