package com.odinrossy.banksystem.exceptions

class UserAlreadyExistException extends RuntimeException {
    UserAlreadyExistException(String message) {
        super(message)
    }
}
