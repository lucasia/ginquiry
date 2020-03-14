package com.lucasia.ginquiry.dao;

public interface NameableRepository<T, ID> {

    T findByName(String name);

}
