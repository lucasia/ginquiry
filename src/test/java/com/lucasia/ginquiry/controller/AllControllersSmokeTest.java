package com.lucasia.ginquiry.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllControllersSmokeTest {

    @Autowired
    private BrandCrudController brandController;

    @Autowired
    private GinCrudController ginController;

    @Autowired
    private UserCrudController userController;

    @Test
    public void contextLoads() throws Exception {

        Assertions.assertNotNull(brandController);

        Assertions.assertNotNull(ginController);

        Assertions.assertNotNull(userController);
    }

}
