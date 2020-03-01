package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
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
import java.net.URISyntaxException;
import java.util.UUID;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GintHttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String ginUrl;


    @BeforeEach
    void setUp() {
        ginUrl = "http://localhost:" + port + GinController.GIN_PATH;
    }

    @Test
    public void testFindBootstrappedBoozesViaHttp() {
        String response = testRestTemplate.getForObject(ginUrl, String.class);

        Assert.assertTrue(response.contains(Booze.ROCK_ROSE_WINTER.getName()));
        Assert.assertTrue(response.contains(Booze.ROCK_ROSE_SPRING.getName()));
    }


    @Test
    public void testAddNewBoozeViaHttpExistingBrand() throws Exception {
        final Booze booze = new Booze(Brand.ROCK_ROSE, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        saveAndAssertSuccess(booze);
    }

    @Test
    public void testAddNewBoozeViaHttpNewBrand() throws Exception {
        // create a new Brand
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        saveAndAssertSuccess(booze);
    }

    public void saveAndAssertSuccess(Booze booze) throws URISyntaxException {
        final HttpEntity<Booze> request = new HttpEntity<>(booze, new HttpHeaders());
        final ResponseEntity<String> results = this.testRestTemplate.postForEntity(new URI(ginUrl),
                request, String.class);

        Assert.assertTrue(results.getStatusCode().is2xxSuccessful());
        Assert.assertNotNull(results.getBody());
        Assert.assertTrue(results.getBody().contains(booze.getName()));
    }

    @Test
    public void testAddNewBoozeViaHttpUsingJson() throws Exception {
        final Booze booze = new Booze(new Brand(UUID.randomUUID().toString()), UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final String results = postUsingJson(booze, booze.getBrand());

        Assert.assertNotNull(results);
        Assert.assertTrue(results.contains(booze.getName()));
        Assert.assertTrue(results.contains(booze.getBrand().getName()));
    }

    @Test
    public void testAddNewBoozeWithNoBrandViaHttpUsingJsonThrowsException() throws Exception {
        final Booze booze = new Booze(new Brand(UUID.randomUUID().toString()), UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final String results = postUsingJson(booze, null); // pass in null Brand to exercise scenario with missing Brand
        Assert.assertTrue(results.contains("Saving empty Brand"));
    }

    public String postUsingJson(Booze booze, Brand brand) throws JSONException, URISyntaxException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JSONObject boozeJson = toJsonObject(booze, brand);

        final HttpEntity<String> request = new HttpEntity<>(boozeJson.toString(), headers);

        return this.testRestTemplate.postForObject(new URI(ginUrl), request, String.class);
    }

    /*
        Creates Json to be posted for example (after -d):
        curl -X POST localhost:8080/gins -H 'Content-type:application/json' -d \
            '{"name":"Test Booze Name","description":"Test Booze Description","brand":{"name":"Test Brand Name"}}'
     */
    public JSONObject toJsonObject(Booze booze, Brand brand) throws JSONException {
        final JSONObject boozeJson = new JSONObject();
        boozeJson.put("id", booze.getId());
        boozeJson.put("name", booze.getName());
        boozeJson.put("description", booze.getDescription());

        if (brand != null) {
            final JSONObject brandJson = BrandHttpRequestTest.toJsonObject(brand);
            boozeJson.put("brand", brandJson);
        }

        log.info("Booze as Json =" + boozeJson.toString());

        return boozeJson;
    }

}
