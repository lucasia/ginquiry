package com.lucasia.ginquiry.util;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource '" + id + "' not found.");
    }
}
