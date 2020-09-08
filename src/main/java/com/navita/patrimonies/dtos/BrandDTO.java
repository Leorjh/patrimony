package com.navita.patrimonies.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.navita.patrimonies.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties
public class BrandDTO {

    private final String name;

    @JsonProperty("brand_id")
    private Long brandId;

    public static Brand toEntity(BrandDTO brandDTO) {
        return Brand.builder()
                .name(brandDTO.getName())
                .brandId(brandDTO.getBrandId())
                .build();
    }

    public static BrandDTO toDto(Brand brand) {
        return BrandDTO.builder()
                .name(brand.getName())
                .brandId(brand.getBrandId())
                .build();
    }
}
