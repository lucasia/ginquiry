package com.lucasia.ginquiry.controller;

import org.hamcrest.Matchers;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class) // run without the server
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private String principalPath = UserController.PRINCIPAL_PATH;

    @Test
    public void testPrincipalSuccessWhenNotLoggedIn() throws Exception {

        final ResultActions resultActions = mockMvc.perform(get(principalPath));

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(IsEmptyString.emptyString()));
    }

    @Test
    @WithMockUser("guest")
    public void testPrincipalSuccessAndUsernameAvailableWhenLoggedIn() throws Exception {

        final ResultActions resultActions = mockMvc.perform(get(principalPath));

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("guest")));
    }


    @Test
    public void testOtherEndpointsReturnUnauthorizedWhenNotLoggedIn() throws Exception {

        final ResultActions resultActions = mockMvc.perform(get("/"+UUID.randomUUID()));

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(Matchers.containsString("")));
    }

}
