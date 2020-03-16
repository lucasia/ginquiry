package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Nameable;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
// @SpringBootTest
// TODO: change this to use a mock, not container
// @AutoConfigureMockMvc
//@DataJpaTest
public abstract class AbstractRepositoryTest<T extends Nameable, R extends NameableJpaRepository<T, Long>> {

    @Test
    public void testFindAll() {
        final T entity = newInstanceRandomName();

        saveEntity(entity);

        List<T> entities = getRepository().findAll();

        // TODO: put this assertion in once not loading default data in this context
        // Assert.assertEquals(1, entities.size());

        MatcherAssert.assertThat(entities, hasItem(entity));
    }

    @Test
    public void testFindEntityByName() {
        final T entity = newInstanceRandomName();

        saveEntity(entity);

        T resultEntity = getNameableRepository().findByName(entity.getName());
        Assertions.assertEquals(entity, resultEntity);
    }

    public void saveEntity(T entity) {
        getRepository().save(entity);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testSavingEntityWithSameNameThrowsException() {
        final T entity = newInstanceRandomName();

        saveEntity(entity);

        // ensure entity name is unique
        final T entitySameName = newInstance(entity.getName());
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> getRepository().save(entitySameName));
    }

    public abstract R getRepository();

    public R getNameableRepository() {
        return getRepository();
    }

    public T newInstanceRandomName() {
        return newInstance(UUID.randomUUID().toString());
    }

    public abstract T newInstance(String name);


}
