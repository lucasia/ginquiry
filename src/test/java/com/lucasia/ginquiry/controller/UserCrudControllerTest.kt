package com.lucasia.ginquiry.controller

import com.lucasia.ginquiry.dao.UserRepository
import com.lucasia.ginquiry.domain.DomainFactory
import com.lucasia.ginquiry.domain.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.web.util.NestedServletException

@WebMvcTest(UserCrudController::class) // run without the server
class UserCrudControllerTest() : AbstractCrudControllerTest<User>(DomainFactory.UserDomainFactory(), UserCrudController.USER_PATH) {

    @MockBean
    private val repository: UserRepository? = null

    override fun getRepository(): UserRepository? {
        return repository
    }

    @Test
    @WithMockUser(GUEST_USER)
    override fun testFindAll() {
        val ex = Assertions.assertThrows(NestedServletException::class.java) { super.testFindAll() }
        Assertions.assertEquals(UnsupportedOperationException::class.java, ex.rootCause.javaClass)
    }

}