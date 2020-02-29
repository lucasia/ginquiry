package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

//@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
