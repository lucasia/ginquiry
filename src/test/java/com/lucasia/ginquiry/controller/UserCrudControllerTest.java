package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.UserRepository;
import com.lucasia.ginquiry.domain.DomainFactory;
import com.lucasia.ginquiry.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.util.NestedServletException;


@WebMvcTest(UserCrudController.class) // run without the server
public class UserCrudControllerTest extends AbstractCrudControllerTest<User> {

    @MockBean
    private UserRepository repository;

    public UserCrudControllerTest() {
        super(new DomainFactory.UserDomainFactory(), UserCrudController.USER_PATH);
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testFindAll() throws Exception {
        NestedServletException ex = Assertions.assertThrows(NestedServletException.class, super::testFindAll);

        Assertions.assertEquals(UnsupportedOperationException.class, ex.getRootCause().getClass());
    }

    @Override
    public UserRepository getRepository() {
        return repository;
    }

}
