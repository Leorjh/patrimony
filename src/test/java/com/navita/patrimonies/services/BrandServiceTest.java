package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.entities.Brand;
import com.navita.patrimonies.respositories.BrandRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrandServiceTest {

    private static final Long ID_BRAND = 1L;
    private static final String NAME_BRAND = "Dell";

    @InjectMocks
    private BrandService service;

    @Mock
    private BrandRepository repository;

    @Test
    public void findByIdWhenSuccess() throws NotFoundException {
        when(repository.findById(ID_BRAND)).thenReturn(Optional.of(buildBrand()));

        var result = service.findById(ID_BRAND);

        assertEquals(result.getName(), NAME_BRAND);
        assertEquals(result.getBrandId(), ID_BRAND);
        verify(repository).findById(ID_BRAND);
    }

    @Test(expected = NotFoundException.class)
    public void findByIdWhenNotFound() throws NotFoundException {
        when(repository.findById(13L)).thenReturn(Optional.empty());

        service.findById(13L);
    }

    @Test
    public void findAllWhenSuccess() {
        when(repository.findAll()).thenReturn(List.of(buildBrand()));

        var result = service.findAll();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), NAME_BRAND);
        assertEquals(result.get(0).getBrandId(), ID_BRAND);
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
        service.deleteById(ID_BRAND);

        verify(repository).deleteById(ID_BRAND);
    }

    @Test
    public void persistBrand() {

        service.persist(BrandDTO.toDto(buildBrand()));

        verify(repository).save(buildBrand());
    }

    private Brand buildBrand() {
        return Brand.builder()
                .brandId(ID_BRAND)
                .name(NAME_BRAND)
                .build();
    }
}
