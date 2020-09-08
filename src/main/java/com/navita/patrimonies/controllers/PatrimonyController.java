package com.navita.patrimonies.controllers;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.dtos.PatrimonyDTO;
import com.navita.patrimonies.entities.Brand;
import com.navita.patrimonies.entities.Patrimony;
import com.navita.patrimonies.services.PatrimonyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrimony")
public class PatrimonyController {

    @Autowired
    private PatrimonyService patrimonyService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public PatrimonyDTO persist(@RequestBody PatrimonyDTO patrimonyDTO) {
        return patrimonyService.persist(patrimonyDTO);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<PatrimonyDTO> findAll() {
        return patrimonyService.findAll();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public PatrimonyDTO findById(@PathVariable("id") Long id) throws NotFoundException {
        return patrimonyService.findById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        patrimonyService.deleteById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}")
    public PatrimonyDTO persist(@PathVariable("id") Long id, @RequestBody PatrimonyDTO patrimonyDTO) {
        patrimonyDTO.setTombo(id);

        PatrimonyDTO patrimonySaved = patrimonyService.persist(patrimonyDTO);
        return patrimonySaved;
    }

}
