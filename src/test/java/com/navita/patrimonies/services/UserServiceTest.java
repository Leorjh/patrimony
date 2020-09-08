package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.UserDTO;
import com.navita.patrimonies.entities.User;
import com.navita.patrimonies.respositories.UserRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Long ID_USER = 1L;
    private static final String LOGIN_USER = "teste@gmail.com";
    private static final String NAME_USER = "Teste usuario";
    private static final String PASSWORD_USER = "Userpassword";

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void findByIdWhenSuccess() throws NotFoundException {
        when(repository.findById(ID_USER)).thenReturn(Optional.of(buildUser()));

        var result = service.findById(ID_USER);

        assertEquals(result.getName(), NAME_USER);
        assertEquals(result.getLogin(), LOGIN_USER);
        assertEquals(result.getPassword(), PASSWORD_USER);
        verify(repository).findById(ID_USER);
    }

    @Test(expected = NotFoundException.class)
    public void findByIdWhenNotFound() throws NotFoundException {
        when(repository.findById(13L)).thenReturn(Optional.empty());

        service.findById(13L);
    }

    @Test
    public void findAllWhenSuccess() {
        when(repository.findAll()).thenReturn(List.of(buildUser()));

        var result = service.findAll();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), NAME_USER);
        assertEquals(result.get(0).getLogin(), LOGIN_USER);
        assertEquals(result.get(0).getPassword(), PASSWORD_USER);
        verify(repository).findAll();
    }

    @Test
    public void findAllWhenListIsEmpty() {
        when(repository.findAll()).thenReturn(List.of());

        var result = service.findAll();

        assertEquals(result.size(), (0));
        verify(repository).findAll();
    }

    @Test
    public void deleteById() {
        service.deleteById(ID_USER);

        verify(repository).deleteById(ID_USER);
    }

    @Test
    public void persistUser() {
        when(repository.save(any())).thenReturn(buildUser());
        when(passwordEncoder.encode(any())).thenReturn(("teste"));

        service.persist(UserDTO.toDto(buildUser()));

        verify(repository).save(any());
    }

    private User buildUser() {
        return User.builder()
                .login(LOGIN_USER)
                .name(NAME_USER)
                .password(PASSWORD_USER)
                .build();
    }
}
