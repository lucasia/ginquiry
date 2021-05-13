package com.lucasia.ginquiry.controller;

import org.hamcrest.Matchers
import org.hamcrest.text.IsEmptyString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@WebMvcTest(UserController::class) // run without the server
class UserControllerTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    private val principalPath: String = UserController.PRINCIPAL_PATH

    @Test
    fun testPrincipalSuccessWhenNotLoggedIn() {
        val resultActions: ResultActions = mockMvc!!.perform(get(principalPath))

        resultActions.andDo(
                print())
                .andExpect(status().isOk)
                .andExpect(content().string(IsEmptyString.emptyString()))
    }

    @Test
    @WithMockUser("guest")
    fun testPrincipalSuccessAndUsernameAvailableWhenLoggedIn() {
        val resultActions: ResultActions = mockMvc!!.perform(get(principalPath))

        resultActions.andDo(
                print())
                .andExpect(status().isOk)
                .andExpect(content().string(Matchers.containsString("guest")))
    }


    @Test
    fun testOtherEndpointsReturnUnauthorizedWhenNotLoggedIn() {

        val resultActions: ResultActions = mockMvc!!.perform(get("/" + UUID.randomUUID()))

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError)
                .andExpect(status().isUnauthorized)
                .andExpect(content().string(Matchers.containsString("")))
    }

}
