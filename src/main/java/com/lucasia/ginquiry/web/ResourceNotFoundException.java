package com.lucasia.ginquiry.web;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class clazz, Long id) {
        super(clazz.getSimpleName() + " '" + id + "' not found.");
    }
}
