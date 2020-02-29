package com.lucasia.ginquiry.web;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource '" + id + "' not found.");
    }
}
