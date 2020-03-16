package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.NameableJpaRepository;
import com.lucasia.ginquiry.util.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Log4j2
public abstract class AbstractCrudController<T, ID extends Long, R extends NameableJpaRepository<T, ID>> {

    private R repository;

    public AbstractCrudController(R repository) {
        this.repository = repository;
    }

    protected AbstractCrudController() {
    }

    // aggregate root

    @GetMapping()
    List<T> all(){
        return getRepository().findAll();
    }

    @PostMapping()
    T newEntity(@RequestBody T newEntity) {
        log.debug("persisting new Entity " + newEntity);

        return getRepository().save(newEntity);
    }

    // single item

    @GetMapping("/{id}")
    T one(@PathVariable ID id) {

        log.debug("finding " + id);

        Optional<T> byId = getRepository().findById(id);

        return byId.orElseThrow(() -> new ResourceNotFoundException(id));
   }

    @GetMapping("/&name={name}")
    T one(@PathVariable String name) {

        log.debug("finding " + name);

        T byName = getRepository().findByName(name);

        if (byName == null) throw new ResourceNotFoundException(name);

        return byName;
    }

    public R getRepository() {
        return repository;
    }


}
