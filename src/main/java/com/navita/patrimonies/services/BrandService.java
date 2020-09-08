package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.entities.Brand;
import com.navita.patrimonies.respositories.BrandRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BrandService {

    @Autowired
    private final BrandRepository brandRepository;

    public Brand persist(BrandDTO brandDTO) {
        var brand = BrandDTO.toEntity(brandDTO);

        return brandRepository.save(brand);
    }

    public List<BrandDTO> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(BrandDTO::toDto)
                .collect(Collectors.toList());
    }

    public BrandDTO findById(Long id) throws NotFoundException {
        return brandRepository.findById(id)
                .map(BrandDTO::toDto)
                .orElseThrow(() -> new NotFoundException("No Brand found for id " + id));
    }

    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
