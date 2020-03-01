package com.lucasia.ginquiry.service;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.service.BoozeService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BoozeServiceTest {

    @Autowired
    private BoozeService boozeService;

    @Test
    public void testAddNewBoozeWithExistingBrandSucceeds() {
        // create a new Brand
        final Brand brand = Brand.ROCK_ROSE;
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        Booze resultBooze = save(booze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);
    }


    @Test
    public void testAddNewBoozeWithNewBrandSucceeds() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final Booze resultBooze = save(booze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);
    }

    @Test
    public void testAddNewBoozeWithExistingBrandOnlySameNameSucceeds() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final Booze resultBooze = save(booze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);

        final Booze anotherBoozeSameBrand = new Booze(new Brand(brand.getName()), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        final Booze resultAnotherBoozeSameBrand = save(anotherBoozeSameBrand);

        Assert.assertEquals(brand, resultAnotherBoozeSameBrand.getBrand());
        Assert.assertEquals(anotherBoozeSameBrand, resultAnotherBoozeSameBrand);

    }

    @Test
    public void testSaveGinWithoutBrandThrowsError() {
        Exception ex = Assert.assertThrows(NullPointerException.class, () -> save(new Booze()));

        Assert.assertTrue(ex.getMessage().contains("Saving empty Brand"));
    }

    public Booze save(Booze booze) {
        return boozeService.save(booze);
    }

}
