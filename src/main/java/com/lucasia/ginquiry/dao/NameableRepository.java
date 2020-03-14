package com.lucasia.ginquiry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface NameableRepository<T, ID> {

    T findByName(String name);

}
