package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.UUID;


@WebMvcTest(BrandCrudController.class) // run without the server
public class BrandCrudControllerTest extends AbstractCrudControllerTest<Brand, BrandRepository> {

    @MockBean
    private BrandRepository repository;

    public BrandCrudControllerTest() {
        super(BrandCrudController.BRAND_PATH);
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindAll() throws Exception {
        testFindAll(Arrays.asList(Brand.ROCK_ROSE, Brand.HENDRICKS));
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindById() throws Exception {
        testFindById(Brand.ROCK_ROSE);
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindByName() throws Exception {
        testFindByName(Brand.ROCK_ROSE);
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testNewSucceeds() throws Exception {
        testAddNewSucceeds(new Brand(UUID.randomUUID().toString()));
    }

    @Override
    public BrandRepository getRepository() {
        return repository;
    }
}
