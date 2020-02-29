package com.lucasia.ginquiry.web;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllControllersSmokeTest {

    @Autowired
    private BrandController brandController;

    @Autowired
    private GinController ginController;

    @Test
    public void contextLoads() throws Exception {

        Assert.assertNotNull(brandController);

        Assert.assertNotNull(ginController);
    }

}
