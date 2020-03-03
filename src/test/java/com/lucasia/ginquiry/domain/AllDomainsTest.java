package com.lucasia.ginquiry.domain;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@Log4j2
public class AllDomainsTest {

    @Test
    public void testMissingBrandNameThrowsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Brand(null));
    }

    @Test
    public void testMissingNameThrowsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Booze(null, "name", "description"));

        Assert.assertThrows(NullPointerException.class, () -> new Booze(new Brand("brand"), null, "description"));

        Assert.assertThrows(NullPointerException.class, () -> new Booze(new Brand("brand"), "name", null));
    }

}
