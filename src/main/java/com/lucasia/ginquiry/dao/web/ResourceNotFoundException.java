package com.lucasia.ginquiry.dao.web;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Could not find resource "  + id);
    }
}
