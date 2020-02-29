package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.domain.Brand;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BrandHttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + BrandController.BRAND_PATH;
    }

    @Test
    public void shouldReturnInitialBrands() throws Exception {
        String response = testRestTemplate.getForObject(baseUrl, String.class);

        Assert.assertTrue(response.contains(Brand.HENDRICKS.getBrandName()));
        Assert.assertTrue(response.contains(Brand.ROCK_ROSE.getBrandName()));
    }


    @Test
    public void shouldAddNewBrand() throws Exception {

        final Brand brand = new Brand(UUID.randomUUID().toString());

        final HttpEntity<Brand> request = new HttpEntity<>(brand, new HttpHeaders());
        final ResponseEntity<String> results = this.testRestTemplate.postForEntity(new URI(baseUrl),
                request, String.class);

        Assert.assertTrue(results.getStatusCode().is2xxSuccessful());
        Assert.assertTrue(results.getBody().contains(brand.getBrandName()));
    }

    @Test
    public void shouldAddNewBrandWithJson() throws Exception {

        final Brand brand = new Brand(UUID.randomUUID().toString());

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JSONObject brandJson = new JSONObject();
        brandJson.put("brandId", brand.getBrandId());
        brandJson.put("brandName", brand.getBrandName());

        final HttpEntity<String> request = new HttpEntity<>(brandJson.toString(), headers);

        final String results = this.testRestTemplate.postForObject(new URI(baseUrl), request, String.class);

        Assert.assertNotNull(results);
        Assert.assertTrue(results.contains(brand.getBrandName()));
    }
}
