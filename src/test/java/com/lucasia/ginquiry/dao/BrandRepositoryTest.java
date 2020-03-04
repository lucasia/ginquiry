package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Brand;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

@SpringBootTest
// TODO: change this to use a mock, not container
// @AutoConfigureMockMvc
//@DataJpaTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testFindAll() {
        final Brand brand = new Brand(UUID.randomUUID().toString());

        brandRepository.save(brand);

        List<Brand> brands = brandRepository.findAll();

        // TODO: put this assertion in once not loading default data in this context
        // Assert.assertEquals(1, brands.size());

        MatcherAssert.assertThat(brands, hasItem(brand));
    }

    @Test
    public void testFindBrandByName() {
        final Brand brand = new Brand(UUID.randomUUID().toString());

        brandRepository.save(brand);

        Brand resultBrand = brandRepository.findByName(brand.getName());
        Assertions.assertEquals(brand, resultBrand);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testSavingBrandWithSameNameThrowsException() {
        final Brand brand = new Brand(UUID.randomUUID().toString());

        brandRepository.save(brand);

        // ensure brand name is unique
        final Brand brandSameName = new Brand(brand.getName());
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> brandRepository.save(brandSameName));
    }

}
