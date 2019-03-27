package com.odinrossy.banksystem.exceptions;

public class UserNullException extends RuntimeException {
    public UserNullException() {
        super("User is null.");
    }
}
