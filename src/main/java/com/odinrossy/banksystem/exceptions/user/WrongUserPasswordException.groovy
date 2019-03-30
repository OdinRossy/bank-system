package com.odinrossy.banksystem.exceptions.user

class WrongUserPasswordException extends RuntimeException {
    WrongUserPasswordException(String message) {
        super(message)
    }
}
