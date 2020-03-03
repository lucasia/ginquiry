package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.BrandClient;
import com.lucasia.ginquiry.Client;
import com.lucasia.ginquiry.domain.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.UUID;


@WebMvcTest(BrandController.class) // run without the server
public class BrandControllerTest extends AbstractControllerTest<Brand> {

    @MockBean
    private BrandClient brandClient;

    public BrandControllerTest() {
        super(BrandController.BRAND_PATH);
    }

    @Test
    public void testFindAll() throws Exception {
        testFindAll(Arrays.asList(Brand.ROCK_ROSE, Brand.HENDRICKS));
    }

    @Test
    public void testFindById() throws Exception {
        testFindById(Brand.ROCK_ROSE);
    }

    @Test
    public void testNewSucceeds() throws Exception {
        testAddNewSucceeds(new Brand(UUID.randomUUID().toString()));
    }

    @Override
    public Client<Brand, Long> getRepository() {
        return brandClient;
    }
}
