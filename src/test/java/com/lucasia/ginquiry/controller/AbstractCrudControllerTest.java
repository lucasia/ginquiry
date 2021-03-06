package com.lucasia.ginquiry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasia.ginquiry.dao.NameableJpaRepository;
import com.lucasia.ginquiry.domain.DomainFactory;
import com.lucasia.ginquiry.domain.Nameable;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
public abstract class AbstractCrudControllerTest<T extends Nameable>{

    public static final String GUEST_USER = "guest";

    @Autowired
    private MockMvc mockMvc;

    private String path;
    private DomainFactory<T> domainFactory;

    public AbstractCrudControllerTest(DomainFactory<T> domainFactory, @NonNull String path) {
        this.domainFactory = domainFactory;
        this.path = path;
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindAll() throws Exception {
        List<T> nameableList = Arrays.asList(domainFactory.newInstanceRandomName(), domainFactory.newInstanceRandomName());
        Mockito.when(getRepository().findAll()).thenReturn(nameableList);

        final ResultActions resultActions = mockMvc.perform(
                get(path))
                .andDo(print())
                .andExpect(status().isOk());


        for (T nameable : nameableList) {
            resultActions.andExpect(content().string(Matchers.containsString(nameable.getName())));
        }
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindById() throws Exception {
        T nameable = domainFactory.newInstanceRandomName();
        Mockito.when(getRepository().findById(1L)).thenReturn(Optional.ofNullable(nameable));

        final ResultActions resultActions = mockMvc.perform(
                get(path + "/1"));

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindByName() throws Exception {
        T nameable = domainFactory.newInstanceRandomName();
        String randomName = UUID.randomUUID().toString();

        Mockito.when(getRepository().findByName(randomName)).thenReturn(nameable);

        final ResultActions resultActions = mockMvc.perform(
                get(path + "/&name="+randomName));

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testNewSucceeds() throws Exception {
        testAddNewSucceeds(domainFactory.newInstanceRandomName());
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testReturn404WhenResourceNotFoundById() throws Exception {
        final ResultActions resultActions = mockMvc.perform(
                get(path + "/1"));

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound())
                .andExpect(content().string(Matchers.containsString("not found")));
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testReturn404WhenResourceNotFoundByName() throws Exception {
        final ResultActions resultActions = mockMvc.perform(
                get(path + "/&name=random"));

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound())
                .andExpect(content().string(Matchers.containsString("not found")));
    }


    @Test
    public void testReturn401WhenNotLoggedIn() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get(path));

        resultActions.andDo(
                print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(IsEmptyString.emptyString()));
    }


    protected void testAddNewSucceeds(T nameable) throws Exception {
        final ResultActions resultActions = saveEntity(nameable);

        resultActions.andDo(
                print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(nameable.getName())));

    }

    protected ResultActions saveEntity(T nameable) throws Exception {
        Mockito.when(getRepository().save(nameable)).thenReturn(nameable);

        final String jsonContent = new ObjectMapper().writeValueAsString(nameable);

        final RequestBuilder requestBuilder = post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON);

        return mockMvc.perform(requestBuilder);
    }

    public abstract NameableJpaRepository<T, Long> getRepository();

    public DomainFactory<T> getDomainFactory() {
        return domainFactory;
    }
}
