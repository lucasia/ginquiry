package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.UserRepository;
import com.lucasia.ginquiry.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.UUID;


@WebMvcTest(UserCrudController.class) // run without the server
public class UserCrudControllerTest extends AbstractCrudControllerTest<User> {

    @MockBean
    private UserRepository repository;

    public UserCrudControllerTest() {
        super(UserCrudController.USER_PATH);
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindAll() throws Exception {
        NestedServletException ex = Assertions.assertThrows(NestedServletException.class, () ->
                testFindAll(Arrays.asList(createUserRandomNameAndPassword(), createUserRandomNameAndPassword())));

        Assertions.assertEquals(UnsupportedOperationException.class, ex.getRootCause().getClass());
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindById() throws Exception {
        testFindById(createUserRandomNameAndPassword());
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testNewSucceeds() throws Exception {
        testAddNewSucceeds(createUserRandomNameAndPassword());
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return repository;
    }

    public static User createUserRandomNameAndPassword() {
        return new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), true);
    }


}
