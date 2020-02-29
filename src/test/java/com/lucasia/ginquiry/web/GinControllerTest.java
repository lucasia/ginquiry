package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GinController.class) // run without the server
public class GinControllerTest extends AbstractControllerTest<Booze> {

    @MockBean
    private BoozeRepository boozeRepository;

    public GinControllerTest() {
        super(GinController.BRAND_PATH);
    }

    @Test
    public void shouldReturnAllGins() throws Exception {
        shouldReturnAll(Arrays.asList(Booze.ROCK_ROSE_WINTER, Booze.ROCK_ROSE_SPRING));
    }


    @Test
    public void shouldReturnOneBrand() throws Exception {
        shouldReturnOne(Booze.ROCK_ROSE_WINTER);
    }


    public BoozeRepository getRepository() {
        return boozeRepository;
    }

}
