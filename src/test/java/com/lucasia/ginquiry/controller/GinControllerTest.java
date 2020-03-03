package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.BoozeClient;
import com.lucasia.ginquiry.BrandClient;
import com.lucasia.ginquiry.Client;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.UUID;


@WebMvcTest(GinController.class) // run without the server
@Log4j2
public class GinControllerTest extends AbstractControllerTest<Booze> {

    @MockBean
    private BoozeClient boozeClient;

    @MockBean
    private BrandClient brandClient;

    @BeforeEach
    void setUp() {
        Assertions.assertNotNull(boozeClient);
    }

    public GinControllerTest() {
        super(GinController.GIN_PATH);
    }

    @Test
    public void testFindAll() throws Exception {
        testFindAll(Arrays.asList(Booze.ROCK_ROSE_WINTER, Booze.ROCK_ROSE_SPRING));
    }

    @Test
    public void testFindById() throws Exception {
        testFindById(Booze.ROCK_ROSE_WINTER);
    }

    @Test
    public void testNewSucceeds() throws Exception {
        final Brand brand = new Brand(UUID.randomUUID().toString());

        Mockito.when(brandClient.save(brand)).thenReturn(brand);

        testAddNewSucceeds(new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @Test
    public void testNewBoozeWithMissingBrandFails() throws Exception {
        Mockito.when(brandClient.save(null)).thenThrow(new NullPointerException());

        // TODO: change to NPE or a named Exception
        Exception ex = Assertions.assertThrows(NestedServletException.class, () -> saveEntity(new Booze()));

        Assertions.assertTrue(ex.getMessage().contains("brand is marked non-null but is null"));
    }

    @Override
    public Client<Booze, Long> getRepository() {
        return boozeClient;
    }
}
