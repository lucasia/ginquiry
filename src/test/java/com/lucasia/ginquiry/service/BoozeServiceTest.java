package com.lucasia.ginquiry.service;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

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

        Booze resultBooze = boozeService.save(booze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);
    }


    @Test
    public void testAddNewBoozeWithNewBrandSucceeds() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final Booze resultBooze = boozeService.save(booze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);
    }

    @Test
    public void testAddNewBoozeWithBrandOfSameName() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        boozeService.save(booze);

        final Booze anotherBoozeSameBrand = new Booze(new Brand(brand.getName()), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        final Booze resultAnotherBoozeSameBrand = boozeService.save(anotherBoozeSameBrand);

        Assert.assertNotNull(resultAnotherBoozeSameBrand.getBrand().getId());   // should have the Id from the db
        Assert.assertEquals(brand, resultAnotherBoozeSameBrand.getBrand());     // should match the original brand
        Assert.assertEquals(anotherBoozeSameBrand, resultAnotherBoozeSameBrand);
    }

    @Test
    public void testAddBoozeListWithBrandOfSameName() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze1 = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());
        final Booze booze2 = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final List<Booze> results = boozeService.saveAll(Arrays.asList(booze1, booze2));

        MatcherAssert.assertThat(results, hasItem(booze1));
        MatcherAssert.assertThat(results, hasItem(booze2));
    }

    @Test
    public void testSaveGinWithoutBrandThrowsError() {
        Exception ex = Assert.assertThrows(NullPointerException.class, () -> boozeService.save(new Booze()));

        Assert.assertTrue(ex.getMessage().contains("Saving empty Brand"));
    }

    @Test
    public void testSaveAllGinsWithSameBrand() {
        Exception ex = Assert.assertThrows(NullPointerException.class, () -> boozeService.save(new Booze()));

        Assert.assertTrue(ex.getMessage().contains("Saving empty Brand"));
    }

}
