package com.lucasia.ginquiry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.domain.Nameable;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.ContentResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
public abstract class AbstractControllerTest<T extends Nameable> {

    @Autowired
    private MockMvc mockMvc;

    private String path;

    public AbstractControllerTest(@NonNull String path) {
        this.path = path;
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
        Mockito.when(getRepository().findById(1L)).thenReturn(Optional.of(nameable));

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
        Mockito.when(getRepository().save(nameable)).thenReturn(nameable);

        final String jsonContent = new ObjectMapper().writeValueAsString(nameable);

        final RequestBuilder requestBuilder = post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON);

        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));

    }


    public abstract JpaRepository<T, Long> getRepository();

}
