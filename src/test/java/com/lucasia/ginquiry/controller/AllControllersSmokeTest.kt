package com.lucasia.ginquiry.controller;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AllControllersSmokeTest {

    @Autowired
    private val brandController: BrandCrudController? = null

    @Autowired
    private val ginController : GinCrudController? = null

    @Autowired
    private val userController : UserCrudController? = null

    @Test
    fun contextLoads() {
        Assertions.assertNotNull(brandController)
        Assertions.assertNotNull(ginController)
        Assertions.assertNotNull(userController)
    }
    
}
