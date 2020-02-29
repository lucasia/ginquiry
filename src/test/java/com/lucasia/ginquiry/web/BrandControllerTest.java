package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;


@WebMvcTest(BrandController.class) // run without the server
public class BrandControllerTest extends AbstractControllerTest<Brand> {

    @MockBean
    private BrandRepository brandRepository;


    public BrandControllerTest() {
        super(BrandController.BRAND_PATH);
    }

    @Test
    public void shouldReturnAllGins() throws Exception {
        shouldReturnAll(Arrays.asList(Brand.ROCK_ROSE, Brand.HENDRICKS));
    }


    @Test
    public void shouldReturnOneBrand() throws Exception {
        shouldReturnOne(Brand.ROCK_ROSE);
    }


    public BrandRepository getRepository() {
        return brandRepository;
    }

}
