package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.web.BrandController;
import com.lucasia.ginquiry.domain.Brand;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void shouldReturnBaseGins() throws Exception {

        String response = testRestTemplate.getForObject("http://localhost:" + port + BrandController.BRAND_PATH, String.class);

        Assert.assertTrue(response.contains(Brand.HENDRICKS.getBrandName()));
        Assert.assertTrue(response.contains(Brand.ROCK_ROSE.getBrandName()));

    }


}
