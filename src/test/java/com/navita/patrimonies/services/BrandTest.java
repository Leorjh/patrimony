package com.navita.patrimonies.services;

import com.navita.patrimonies.dtos.BrandDTO;
import com.navita.patrimonies.respositories.BrandRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.text.ParseException;

public class BrandTest {

    @InjectMocks
    @Resource
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    private static final long uuid = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void persistTestSuccess() {
        BrandDTO brandDTO = BrandDTO.builder()
                .name("Teste")
                .brandId(uuid)
                .build();

        brandService.persist(brandDTO);
    }

    @Test
    public void findByIdReturnOne() throws ParseException, NotFoundException {
        Long id = uuid;
        //Brand brand = buildBrand ( uuid );
        BrandService brandService = Mockito.mock(BrandService.class);
    }
}
