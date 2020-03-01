package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.UUID;


@WebMvcTest(GinController.class) // run without the server
@Log4j2
public class GinControllerTest extends AbstractControllerTest<Booze> {

    @MockBean
    private BoozeRepository boozeRepository;

    @MockBean
    private BrandRepository brandRepository;


    @BeforeEach
    void setUp() {
        Assert.assertNotNull(boozeRepository);
    }

    public GinControllerTest() {
        super(GinController.GIN_PATH);
    }

    @Test
    public void shouldReturnAllGins() throws Exception {
        testFindAll(Arrays.asList(Booze.ROCK_ROSE_WINTER, Booze.ROCK_ROSE_SPRING));
    }

    @Test
    public void shouldReturnOneBrand() throws Exception {
        testFindById(Booze.ROCK_ROSE_WINTER);
    }

    @Test
    public void testNewSucceeds() throws Exception {
        testAddNewSucceeds(new Booze(new Brand(UUID.randomUUID().toString()), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

/*    @Test
    // TODO:add test for empty Brand
    public void testNewBoozeWithMissingBrandFails() throws Exception {
        Exception ex = Assert.assertThrows(NullPointerException.class, () -> save(new Booze()));

        Assert.assertTrue(ex.getMessage().contains("Saving empty Brand"));
    }*/

    @Override
    public JpaRepository<Booze, Long> getRepository() {
        return boozeRepository;
    }

}
