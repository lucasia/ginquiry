package com.lucasia.ginquiry.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
public abstract class AbstractController<T, ID> {

    // aggregate root

    @GetMapping()
    List<T> allBrands(){
        return getBrandRepository ().findAll();
    }

    @PostMapping()
    T newBrand(@RequestBody T newBrand) {
        return getBrandRepository().save(newBrand);
    }

    // single item

    @GetMapping("/{id}")
    T one(@PathVariable ID id) {

        log.debug("finding " + id);

        Optional<T> byId = getBrandRepository ().findById(id);

        return byId.orElseThrow(() -> new ResourceNotFoundException(id));
   }

   public abstract JpaRepository<T, ID> getBrandRepository ();

}
