package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.domain.DomainFactory;
import com.lucasia.ginquiry.service.BoozeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.util.NestedServletException;

import java.util.UUID;


@WebMvcTest(GinCrudController.class) // run without the server
@Log4j2
public class GinControllerTest extends AbstractCrudControllerTest<Booze> {

    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private BoozeRepository boozeRepository;

    @MockBean
    private BoozeService boozeService;

    @BeforeEach
    void setUp() {
        Assertions.assertNotNull(boozeService);
    }

    public GinControllerTest() {
        super(new DomainFactory.BoozeDomainFactory(new Brand(UUID.randomUUID().toString())), GinCrudController.GIN_PATH);
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testNewSucceeds() throws Exception {
        final Brand brand = new Brand(UUID.randomUUID().toString());

        Mockito.when(brandRepository.save(brand)).thenReturn(brand);

        testAddNewSucceeds(new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @Test
    @WithMockUser(GUEST_USER)
    public void testNewBoozeWithMissingBrandFails() throws Exception {
        Mockito.when(brandRepository.save(null)).thenThrow(new NullPointerException());

        // TODO: change to NPE or a named Exception
        Exception ex = Assertions.assertThrows(NestedServletException.class, () -> saveEntity(new Booze()));

        Assertions.assertTrue(ex.getMessage().contains("brand is marked non-null but is null"));
    }

    @Override
    public BoozeRepository getRepository() {
        return boozeRepository;
    }
}
