package com.lucasia.ginquiry.dao

import com.lucasia.ginquiry.domain.Booze
import com.lucasia.ginquiry.domain.Brand
import com.lucasia.ginquiry.domain.DomainFactory
import lombok.extern.log4j.Log4j2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@DataJpaTest
@Log4j2
open class BoozeRepositoryTest : AbstractRepositoryTest<Booze>(DomainFactory.BoozeDomainFactory(Brand(UUID.randomUUID().toString()))) {

    @Autowired
    val brandRepository: BrandRepository? = null

    @Autowired
    val boozeRepository: BoozeRepository? = null


    override fun getRepository(): BoozeRepository? {
        return boozeRepository
    }


    override fun saveEntity(entity: Booze?) {
        brandRepository!!.saveAndFlush(entity?.brand)
        boozeRepository!!.saveAndFlush(entity)
    }


    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    override fun testSavingEntityWithSameNameThrowsException() {
        val brand: Brand = Brand(UUID.randomUUID().toString())
        val booze: Booze = Booze(brand, UUID.randomUUID().toString(), UUID.randomUUID().toString())

        brandRepository!!.saveAndFlush(brand)
        boozeRepository!!.saveAndFlush(booze)

        boozeRepository.flush()

        val boozeSameName: Booze = Booze(brand, booze.name, UUID.randomUUID().toString())
        Assertions.assertThrows(DataIntegrityViolationException::class.java) { boozeRepository.save(boozeSameName) }

    }

/*
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
*/
}
