package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.Client;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Log4j2
public abstract class AbstractController<T, ID extends Long> {

    private Client<T, ID> client;

    public AbstractController(Client<T, ID> client) {
        this.client = client;
    }

    protected AbstractController() {
    }

    // aggregate root

    @GetMapping()
    List<T> all(){
        return getClient().findAll();
    }

    @PostMapping()
    T newEntity(@RequestBody T newEntity) {
        log.debug("persisting new Entity " + newEntity);

        return getClient().save(newEntity);
    }

    // single item

    @GetMapping("/{id}")
    T one(@PathVariable ID id) {

        log.debug("finding " + id);

        final T entity = getClient().findById(id);

        if (entity == null) throw new ResourceNotFoundException(id);

        return entity;

/*
        Optional<T> byId = getClient().findById(id);

        return byId.orElseThrow(() -> new ResourceNotFoundException(id));
*/
   }

    public Client<T, ID> getClient() {
        return client;
    }
}
