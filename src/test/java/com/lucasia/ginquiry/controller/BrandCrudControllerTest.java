package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.domain.DomainFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.UUID;


@WebMvcTest(BrandCrudController.class) // run without the server
public class BrandCrudControllerTest extends AbstractCrudControllerTest<Brand> {

    @MockBean
    private BrandRepository repository;

    public BrandCrudControllerTest() {
        super(new DomainFactory.BrandDomainFactory(), BrandCrudController.BRAND_PATH);
    }

    @Override
    public BrandRepository getRepository() {
        return repository;
    }
}
