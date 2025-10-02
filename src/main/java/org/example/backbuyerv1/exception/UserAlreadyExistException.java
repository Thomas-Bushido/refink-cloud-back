package org.example.backbuyerv1.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User Already Exist");
    }
}
