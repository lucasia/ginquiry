package com.lucasia.ginquiry.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasia.ginquiry.controller.BrandCrudController;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@AutoConfigureMockMvc
@Log4j2
public class BrandHttpITest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + BrandCrudController.BRAND_PATH;

        testRestTemplate = testRestTemplate.withBasicAuth("guest", "guest");
    }

    @Test
    public void testFindBootstrappedBrandsViaHttp() throws Exception {
        String response = testRestTemplate.getForObject(baseUrl, String.class);

        log.debug(response);

        Assertions.assertTrue(response.contains(Brand.HENDRICKS.getName()));
        Assertions.assertTrue(response.contains(Brand.ROCK_ROSE.getName()));
    }


    @Test
    public void testAddNewBrandViaHttp() throws Exception {

        final Brand brand = new Brand(UUID.randomUUID().toString());

        final HttpEntity<Brand> request = new HttpEntity<>(brand, new HttpHeaders());
        final ResponseEntity<String> results = this.testRestTemplate.postForEntity(new URI(baseUrl),
                request, String.class);

        Assertions.assertTrue(results.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(results.getBody().contains(brand.getName()));
    }

    @Test
    public void testAddNewBrandViaHttpUsingJson() throws Exception {
        final Brand brand = new Brand(UUID.randomUUID().toString());

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final String brandJson = new ObjectMapper().writeValueAsString(brand);

        final HttpEntity<String> request = new HttpEntity<>(brandJson, headers);

        final String results = this.testRestTemplate.postForObject(new URI(baseUrl), request, String.class);

        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.contains(brand.getName()));
    }

}