package com.odinrossy.banksystem.exceptions.user

class UserAlreadyExistException extends RuntimeException {
    UserAlreadyExistException(String message) {
        super(message)
    }
}
