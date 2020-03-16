package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.domain.DomainFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

@DataJpaTest
@Log4j2
public class BoozeRepositoryTest extends AbstractRepositoryTest<Booze>{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BoozeRepository boozeRepository;

    public BoozeRepositoryTest() {
        super(new DomainFactory.BoozeDomainFactory(new Brand(UUID.randomUUID().toString())));
    }

    @Override
    public BoozeRepository getRepository() {
        return boozeRepository;
    }


    @Override
    public void saveEntity(Booze entity) {
        brandRepository.saveAndFlush(entity.getBrand());
        boozeRepository.saveAndFlush(entity);
    }


    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testSavingEntityWithSameNameThrowsException() {
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
