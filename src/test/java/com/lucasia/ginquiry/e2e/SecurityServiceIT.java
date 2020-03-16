package com.lucasia.ginquiry.e2e;

import com.lucasia.ginquiry.controller.UserCrudController;
import com.lucasia.ginquiry.dao.UserRepository;
import com.lucasia.ginquiry.domain.DomainFactory;
import com.lucasia.ginquiry.domain.User;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    // uses the full SpringBoot context to include Security context
@AutoConfigureMockMvc
public class SecurityServiceIT {

    @Autowired
    private MockMvc mockMvc;

    private String path = UserCrudController.USER_PATH;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testWithoutCredentialsFails() throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = mockFindByid(1L);

        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(IsEmptyString.emptyString()));

    }

    public MockHttpServletRequestBuilder mockFindByid(Long id) throws Exception {
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(new DomainFactory.UserDomainFactory().newInstanceRandomName()));

        return get(path+"/"+id);
    }

    @Test
    @WithMockUser("guest")
    public void testPrincipalSuccessAndUsernameAvailableWhenLoggedIn() throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = mockFindByid(1L);

        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andDo(
                print())
                .andExpect(status().isOk());
    }

    @Test
    public void testWithCredentialsSucceedsWithUser() throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = mockFindByid(1L).with(user("guest"));

        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andDo(
                print())
                .andExpect(status().isOk());
    }

    @Test
    public void testWithCredentialsSucceedsWithAuthentication() throws Exception {
        final String password = new BCryptPasswordEncoder().encode("guest");

        Mockito.when(userRepository.findByName("guest")).thenReturn(new User("guest", password, true));
        final MockHttpServletRequestBuilder requestBuilder = mockFindByid(1L).with(httpBasic("guest", "guest"));

        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andDo(
                print())
                .andExpect(status().isOk());
    }

}
