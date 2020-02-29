package com.lucasia.ginquiry.domain;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

@Log4j2
public class BoozeTest {

    @Test
    public void testBrand() {
        Assert.assertEquals(Brand.ROCK_ROSE, new Brand(Brand.BRAND_NAME));

        Assert.assertThrows(NullPointerException.class, () -> new Brand(null));
    }

    @Test
    public void testBooze() {
        final Booze booze = new Booze(1L, new Brand(Brand.BRAND_NAME), Booze.NAME, Booze.DESCRIPTION);
        Assert.assertEquals(Booze.ROCK_ROSE_WINTER, booze);

        Assert.assertThrows(NullPointerException.class, () -> new Booze(null, null, null, null));
    }

}
