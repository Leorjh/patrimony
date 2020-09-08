package com.navita.patrimonies.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "USER_UNIQUE_LOGIN", columnNames = {"login"})})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;

}
