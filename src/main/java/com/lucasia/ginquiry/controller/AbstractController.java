package com.lucasia.ginquiry.controller;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
public abstract class AbstractController<T, ID> {

    private final JpaRepository<T, ID> repository;

    public AbstractController(@NonNull JpaRepository<T, ID> getRepository) {
        this.repository = getRepository;
    }

    // aggregate root

    @GetMapping()
    List<T> all(){
        return getRepository().findAll();
    }

    @PostMapping()
    T newEntity(@RequestBody T newEntity) {
        return getRepository().save(newEntity);
    }

    // single item

    @GetMapping("/{id}")
    T one(@PathVariable ID id) {

        log.debug("finding " + id);

        Optional<T> byId = getRepository().findById(id);

        return byId.orElseThrow(() -> new ResourceNotFoundException(id));
   }


    public JpaRepository<T, ID> getRepository() {
        return repository;
    }
}
