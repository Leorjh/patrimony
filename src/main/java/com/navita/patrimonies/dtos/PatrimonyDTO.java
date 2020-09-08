package com.navita.patrimonies.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.navita.patrimonies.entities.Brand;
import com.navita.patrimonies.entities.Patrimony;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties
public class PatrimonyDTO {

    private final String name;

    private final BrandDTO brand;

    private final String description;

    private Long tombo;

    public static Patrimony toEntity(PatrimonyDTO patrimonyDTO) {
        return Patrimony.builder()
                .name(patrimonyDTO.getName())
                .brand(Brand.builder().brandId(patrimonyDTO.getBrand().getBrandId()).build())
                .description(patrimonyDTO.getDescription())
                .tombo(patrimonyDTO.getTombo())
                .build();
    }

    public static PatrimonyDTO toDto(Patrimony patrimony) {
        return PatrimonyDTO.builder()
                .name(patrimony.getName())
                .brand(BrandDTO.toDto(patrimony.getBrand()))
                .description(patrimony.getDescription())
                .tombo(patrimony.getTombo())
                .build();
    }
}
