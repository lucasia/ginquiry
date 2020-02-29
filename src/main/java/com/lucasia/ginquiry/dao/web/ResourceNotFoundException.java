package com.lucasia.ginquiry.dao.web;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class clazz, Long id) {
        super(clazz.getSimpleName() + " '" + id + "' not found.");
    }
}
