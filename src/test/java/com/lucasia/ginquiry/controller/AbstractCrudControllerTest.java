package com.lucasia.ginquiry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasia.ginquiry.domain.Nameable;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@WithMockUser("guest")
public abstract class AbstractCrudControllerTest<T extends Nameable> {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private String path;

    public AbstractCrudControllerTest(@NonNull String path) {
        this.path = path;
    }

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    public void testFindAll(List<T> nameableList) throws Exception {
        Mockito.when(getRepository().findAll()).thenReturn(nameableList);

        final ResultActions resultActions = mockMvc.perform(
                get(path))
                .andDo(print())
                .andExpect(status().isOk());


        for (T nameable : nameableList) {
            resultActions.andExpect(content().string(Matchers.containsString(nameable.getName())));
        }
    }

    public void testFindById(T nameable) throws Exception {
        Mockito.when(getRepository().findById(1L)).thenReturn(Optional.ofNullable(nameable));

        final ResultActions resultActions = mockMvc.perform(
                get(path + "/1"));

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));
    }

    @Test
    public void testReturn404WhenBrandNotFound() throws Exception {
        final ResultActions resultActions = mockMvc.perform(
                get(path + "/1"));

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString("not found")));
    }

    public void testAddNewSucceeds(T nameable) throws Exception {
        final ResultActions resultActions = saveEntity(nameable);

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));

    }

    public ResultActions saveEntity(T nameable) throws Exception {
        Mockito.when(getRepository().save(nameable)).thenReturn(nameable);

        final String jsonContent = new ObjectMapper().writeValueAsString(nameable);

        final RequestBuilder requestBuilder = post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON);

        return mockMvc.perform(requestBuilder);
    }


    public abstract JpaRepository<T, Long> getRepository();

}
