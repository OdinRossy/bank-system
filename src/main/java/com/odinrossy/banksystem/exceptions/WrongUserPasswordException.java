package com.odinrossy.banksystem.exceptions;

public class WrongUserPasswordException extends RuntimeException {
    public WrongUserPasswordException(String message) {
        super(message);
    }
}
