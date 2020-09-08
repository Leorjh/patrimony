package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.dtos.PatrimonyDTO;
import com.navita.patrimonies.entities.Brand;
import com.navita.patrimonies.entities.Patrimony;
import com.navita.patrimonies.respositories.PatrimonyRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatrimonyServiceTest {

    private static Long ID_PATRIMONY = 1L;
    private static String NAME_PATRIMONY = "Computador";
    private static String DESCRIPTION_PATRIMONY = "Computador Dell XPTO";
    private static Long TOMBO_PATRIMONY = 2L;
    private static Long ID_BRAND = 1L;
    private static String NAME_BRAND = "Dell";

    @InjectMocks
    private PatrimonyService service;

    @Mock
    private PatrimonyRepository repository;

    @Test
    public void findByIdWhenSuccess() throws NotFoundException {
        when(repository.findById(ID_PATRIMONY)).thenReturn(Optional.of(buildPatrimony()));

        var result = service.findById(ID_PATRIMONY);

        assertEquals(result.getName(), NAME_PATRIMONY);
        assertEquals(result.getBrand().getBrandId(), ID_BRAND);
        assertEquals(result.getDescription(), DESCRIPTION_PATRIMONY);
        assertEquals(result.getTombo(), TOMBO_PATRIMONY);
        verify(repository).findById(ID_PATRIMONY);
    }

    @Test(expected = NotFoundException.class)
    public void findByIdWhenNotFound() throws NotFoundException {
        when(repository.findById(13L)).thenReturn(Optional.empty());

        service.findById(13L);
    }

    @Test
    public void findAllWhenSuccess() {
        when(repository.findAll()).thenReturn(List.of(buildPatrimony()));

        var result = service.findAll();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), NAME_PATRIMONY);
        assertEquals(result.get(0).getBrand().getBrandId(), ID_BRAND);
        assertEquals(result.get(0).getDescription(), DESCRIPTION_PATRIMONY);
        assertEquals(result.get(0).getTombo(), TOMBO_PATRIMONY);
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
        service.deleteById(ID_PATRIMONY);

        verify(repository).deleteById(ID_PATRIMONY);
    }

    @Test
    public void persistPatrimony() {
        when(repository.save(any())).thenReturn(buildPatrimony());

        service.persist(PatrimonyDTO.toDto(buildPatrimony()));

        verify(repository).save(any());
    }

    private Patrimony buildPatrimony() {
        return Patrimony.builder()
                .name(NAME_PATRIMONY)
                .description(DESCRIPTION_PATRIMONY)
                .brand(buildBrand())
                .tombo(TOMBO_PATRIMONY)
                .build();
    }

    private Brand buildBrand() {
        return Brand.builder()
                .brandId(ID_BRAND)
                .name(NAME_BRAND)
                .build();
    }
}
