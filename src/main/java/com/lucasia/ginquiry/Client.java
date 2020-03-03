package com.lucasia.ginquiry;

import com.lucasia.ginquiry.domain.Brand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface Client<T, ID extends Long> {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    List<T> findAll();

    @RequestMapping (method = RequestMethod.GET, path = "/{id}")
    T findById(@PathVariable("id") Long id);

    @RequestMapping (method = RequestMethod.POST, path = "/")
    T save(T newEntity);


}
