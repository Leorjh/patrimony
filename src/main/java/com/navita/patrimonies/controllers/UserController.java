package com.navita.patrimonies.controllers;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.dtos.PatrimonyDTO;
import com.navita.patrimonies.dtos.UserDTO;
import com.navita.patrimonies.entities.User;
import com.navita.patrimonies.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public UserDTO persist(@RequestBody UserDTO userDTO) {
        User userSaved = userService.persist(userDTO);
        return UserDTO.toDto(userSaved);
    }
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") Long id) throws NotFoundException {
        return userService.findById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

}
