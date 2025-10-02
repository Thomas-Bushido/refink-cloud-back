package org.example.backbuyerv1.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Not Found");
    }
}
