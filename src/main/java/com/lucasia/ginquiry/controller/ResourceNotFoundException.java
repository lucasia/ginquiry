package com.lucasia.ginquiry.controller;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource '" + id + "' not found.");
    }
}
