package com.lucasia.ginquiry;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.service.BoozeService;
import com.lucasia.ginquiry.service.BoozeServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
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

        log.info("------------------------------------- " + resultBooze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);
    }


    @Test
    public void testAddNewBoozeWithNewBrandSucceeds() {
        // create a new Brand
        final Brand brand = new Brand(UUID.randomUUID().toString());
        final Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        Booze resultBooze = save(booze);

        log.info("------------------------------------- " + resultBooze);

        Assert.assertNotNull(resultBooze);
        Assert.assertEquals(booze, resultBooze);
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
