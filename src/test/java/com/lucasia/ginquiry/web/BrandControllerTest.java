package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.dao.web.BrandController;
import com.lucasia.ginquiry.domain.Brand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
// run with the server
@SpringBootTest
@AutoConfigureMockMvc
*/
@WebMvcTest(BrandController.class) // run without the server
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandRepository brandRepository;

    @Test
    public void shouldReturnBaseGins() throws Exception {

        Mockito.when(brandRepository.findAll()).thenReturn(Arrays.asList(Brand.ROCK_ROSE, Brand.HENDRICKS));

        final ResultActions resultActions = this.mockMvc.perform(
                get(BrandController.BRAND_PATH));

        resultActions.andDo(
                print()).andExpect(
                status().isOk()).andExpect(
                content().string(Matchers.containsString(Brand.ROCK_ROSE.getBrandName()))).andExpect(
                content().string(Matchers.containsString(Brand.HENDRICKS.getBrandName())));

    }
}
