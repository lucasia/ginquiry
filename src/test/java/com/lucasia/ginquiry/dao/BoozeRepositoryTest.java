package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.GinquiryApplication;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.UUID;

//@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoozeRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BoozeRepository boozeRepository;

    @Test
    public void testFindAll() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        brandRepository.saveAndFlush(brand);
        boozeRepository.saveAndFlush(booze);

        List<Booze> boozes = boozeRepository.findAll();

        // TODO: put this assertion in once not loading default data in this context
        // Assert.assertEquals(boozes.size(), 1);

        MatcherAssert.assertThat(boozes, hasItem(booze));
    }

}
