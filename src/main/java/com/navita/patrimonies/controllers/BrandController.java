package com.navita.patrimonies.controllers;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.dtos.PatrimonyDTO;
import com.navita.patrimonies.entities.Brand;
import com.navita.patrimonies.services.BrandService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public BrandDTO persist(@RequestBody BrandDTO brandDTO) {
        Brand brandSaved = brandService.persist(brandDTO);
        return BrandDTO.toDto(brandSaved);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<BrandDTO> findAll() {
        return brandService.findAll();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public BrandDTO findById(@PathVariable("id") Long id) throws NotFoundException {
        return brandService.findById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        brandService.deleteById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}")
    public BrandDTO persist(@PathVariable("id") Long id, @RequestBody BrandDTO brandDTO) {
        brandDTO.setBrandId(id);

        Brand brandSaved = brandService.persist(brandDTO);
        return BrandDTO.toDto(brandSaved);
    }

}
