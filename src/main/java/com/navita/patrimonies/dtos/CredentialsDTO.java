package com.navita.patrimonies.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CredentialsDTO {
    private String login;
    private String password;
}
