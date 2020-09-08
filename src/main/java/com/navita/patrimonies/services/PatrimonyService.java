package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.PatrimonyDTO;
import com.navita.patrimonies.respositories.PatrimonyRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PatrimonyService {

    @Autowired
    private final PatrimonyRepository patrimonyRepository;

    public PatrimonyDTO persist(PatrimonyDTO patrimonyDTO) {
        var patrimony = PatrimonyDTO.toEntity(patrimonyDTO);

        return PatrimonyDTO.toDto(patrimonyRepository.save(patrimony));
    }

    public List<PatrimonyDTO> findAll() {
        return patrimonyRepository.findAll()
                .stream()
                .map(PatrimonyDTO::toDto)
                .collect(Collectors.toList());
    }

    public PatrimonyDTO findById(Long id) throws NotFoundException {
        return patrimonyRepository.findById(id)
                .map(PatrimonyDTO::toDto)
                .orElseThrow(() -> new NotFoundException("No Patrimony found for id " + id));
    }

    public void deleteById(Long id) {
        patrimonyRepository.deleteById(id);
    }
}
