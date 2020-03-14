package com.lucasia.ginquiry.dao;

public interface NameableRepository<T> {

    T findByName(String name);

}
