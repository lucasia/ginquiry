package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.domain.Nameable;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

@DataJpaTest
@Log4j2
public class BoozeRepositoryTest extends AbstractRepositoryTest<Booze>{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BoozeRepository boozeRepository;

    @Override
    public JpaRepository<Booze, Long> getRepository() {
        return boozeRepository;
    }

    @Override
    public NameableRepository<Booze, Long> getNameableRepository() {
        return boozeRepository;
    }

    @Override
    public Booze newInstance(String name) {
        final Brand brand = new Brand(UUID.randomUUID().toString());
        return new Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString());
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
