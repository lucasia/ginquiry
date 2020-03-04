package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

@DataJpaTest
@Log4j2
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

    @Test
    public void testFindBoozeByName() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        brandRepository.saveAndFlush(brand);
        boozeRepository.saveAndFlush(booze);

        final Booze foundBooze = boozeRepository.findByName(booze.getName());

        Assertions.assertEquals(booze, foundBooze);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testSavingBoozeWithSameNameThrowsException() {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        Booze booze = new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        brandRepository.saveAndFlush(brand);
        boozeRepository.saveAndFlush(booze);

        boozeRepository.flush();

        // ensure uniqueness
        final Booze boozeSameName = new Booze(brand, booze.getName(), UUID.randomUUID().toString());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> boozeRepository.save(boozeSameName));
    }
}
