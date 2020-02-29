package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GintHttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String ginUrl;
    private String brandUrl;

    @BeforeEach
    void setUp() {
        ginUrl = "http://localhost:" + port + GinController.GIN_PATH;
        brandUrl = "http://localhost:" + port + BrandController.BRAND_PATH;

    }

    @Test
    public void shouldReturnInitialBrands() {
        String response = testRestTemplate.getForObject(ginUrl, String.class);

        Assert.assertTrue(response.contains(Booze.ROCK_ROSE_WINTER.getName()));
        Assert.assertTrue(response.contains(Booze.ROCK_ROSE_SPRING.getName()));
    }


    @Test
    public void shouldAddNewGinExistingBrand() throws Exception {

        final Booze booze = new Booze(Brand.ROCK_ROSE, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final HttpEntity<Booze> request = new HttpEntity<>(booze, new HttpHeaders());
        final ResponseEntity<String> results = this.testRestTemplate.postForEntity(new URI(ginUrl),
                request, String.class);

        Assert.assertTrue(results.getStatusCode().is2xxSuccessful());
        Assert.assertNotNull(results.getBody());
        Assert.assertTrue(results.getBody().contains(booze.getName()));
    }

    @Test
    public void shouldAddNewGinNewBrand() throws Exception {
        // create a new Brand
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final HttpEntity<Brand> brandRequest = new HttpEntity<>(brand, new HttpHeaders());
        final ResponseEntity<Brand> brandPostResponse = this.testRestTemplate.postForEntity(new URI(brandUrl),
                brandRequest, Brand.class);

        // use the fetched Brand as this has the ID
        final Brand fetchedBrand = brandPostResponse.getBody();
        Assert.assertTrue(brandPostResponse.getStatusCode().is2xxSuccessful());
        Assert.assertNotNull(fetchedBrand);
        Assert.assertEquals(brand.getName(), fetchedBrand.getName());
        Assert.assertNotNull(fetchedBrand.getId());

        final Booze booze = new Booze(fetchedBrand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final HttpEntity<Booze> request = new HttpEntity<>(booze, new HttpHeaders());
        final ResponseEntity<String> boozePostResponse = this.testRestTemplate.postForEntity(new URI(ginUrl),
                request, String.class);

        Assert.assertTrue(boozePostResponse.getStatusCode().is2xxSuccessful());
        Assert.assertNotNull(boozePostResponse.getBody());
        Assert.assertTrue(boozePostResponse.getBody().contains(booze.getName()));
    }

    /*
    @Test
    public void shouldAddNewBrandWithJson() throws Exception {

        final Brand brand = new Brand(UUID.randomUUID().toString());

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JSONObject brandJson = new JSONObject();
        brandJson.put("id", brand.getId());
        brandJson.put("name", brand.getName());

        final HttpEntity<String> request = new HttpEntity<>(brandJson.toString(), headers);

        final String results = this.testRestTemplate.postForObject(new URI(baseUrl), request, String.class);

        Assert.assertNotNull(results);
        Assert.assertTrue(results.contains(brand.getName()));
    }

     */
}
