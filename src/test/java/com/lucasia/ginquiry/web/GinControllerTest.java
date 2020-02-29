package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.domain.Booze;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(GinController.class) // run without the server
public class GinControllerTest extends AbstractControllerTest<Booze> {

    @MockBean
    private BoozeRepository boozeRepository;

    public GinControllerTest() {
        super(GinController.GIN_PATH);
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
