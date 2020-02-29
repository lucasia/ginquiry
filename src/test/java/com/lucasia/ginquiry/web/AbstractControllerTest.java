package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.domain.Nameable;
import lombok.NonNull;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractControllerTest<T extends Nameable> {

    @Autowired
    private MockMvc mockMvc;

//    private JpaRepository<T, Long> jpaRepository;

    private String path;

    public AbstractControllerTest(@NonNull String path) {
        this.path = path;
    }


    public void shouldReturnAll(List<T> nameableList) throws Exception {
        Mockito.when(getRepository().findAll()).thenReturn(nameableList);

        final ResultActions resultActions = mockMvc.perform(
                get(path))
                .andDo(print())
                .andExpect(status().isOk());


        for (T nameable : nameableList) {
            resultActions.andExpect(content().string(Matchers.containsString(nameable.getName())));
        }
    }

    public void shouldReturnOne(T nameable) throws Exception {
        Mockito.when(getRepository().findById(1L)).thenReturn(Optional.of(nameable));

        final ResultActions resultActions = mockMvc.perform(
                get(path + "/1"));

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));
    }

    @Test
    public void shouldReturn404WhenBrandNotFound() throws Exception {
        final ResultActions resultActions = mockMvc.perform(
                get(path + "/1"));

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(404))
                .andExpect(content().string(Matchers.containsString("not found")));
    }

    public abstract JpaRepository<T, Long> getRepository();

}
