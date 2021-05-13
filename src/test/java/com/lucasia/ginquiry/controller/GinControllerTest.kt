package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BoozeRepository
import com.lucasia.ginquiry.dao.BrandRepository
import com.lucasia.ginquiry.domain.Booze
import com.lucasia.ginquiry.domain.Brand
import com.lucasia.ginquiry.domain.DomainFactory
import com.lucasia.ginquiry.service.BoozeService
import lombok.extern.log4j.Log4j2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.web.util.NestedServletException
import java.util.*


@WebMvcTest(GinCrudController::class) // run without the server
@Log4j2
class GinControllerTest() : AbstractCrudControllerTest<Booze>(DomainFactory.BoozeDomainFactory(Brand(UUID.randomUUID().toString())), GinCrudController.GIN_PATH) {

    @MockBean
    private val brandRepository : BrandRepository? = null

    @MockBean
    private val boozeRepository : BoozeRepository? = null

    @MockBean
    private val boozeService : BoozeService? = null

    @BeforeEach
    fun setUp() {
        Assertions.assertNotNull(boozeService);
    }


    @Test
    @WithMockUser(GUEST_USER)
    override fun testNewSucceeds() {
        val brand =  Brand(UUID.randomUUID().toString())

        Mockito.`when`(brandRepository?.save(brand)).thenReturn(brand)

        testAddNewSucceeds(Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @Test
    @WithMockUser(GUEST_USER)
    fun testNewBoozeWithMissingBrandFails() {
        Mockito.`when`(brandRepository?.save(null)).thenThrow(NullPointerException());

        // TODO: change to NPE or a named Exception
        val ex: Exception = Assertions.assertThrows(NestedServletException::class.java) { saveEntity(Booze()) }

        Assertions.assertTrue(ex.message!!.contains("brand is marked non-null but is null"))
    }

    override fun getRepository(): BoozeRepository? {
        return boozeRepository;
    }
}
