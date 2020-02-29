package com.lucasia.ginquiry.web;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BrandControllerSmokeTest {

    @Autowired
    private BrandController brandController;

    @Test
    public void contextLoads() throws Exception {

        Assert.assertNotNull(brandController);
    }

}
