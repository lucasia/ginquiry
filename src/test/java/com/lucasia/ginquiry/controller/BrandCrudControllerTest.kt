package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository
import com.lucasia.ginquiry.domain.Brand
import com.lucasia.ginquiry.domain.DomainFactory
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean


@WebMvcTest(BrandCrudController::class) // run without the server
class BrandCrudControllerTest() : AbstractCrudControllerTest<Brand>(DomainFactory.BrandDomainFactory(), BrandCrudController.BRAND_PATH) {

    @MockBean
    private val repository: BrandRepository? = null

    override fun getRepository(): BrandRepository? {
        return repository;
    }

}