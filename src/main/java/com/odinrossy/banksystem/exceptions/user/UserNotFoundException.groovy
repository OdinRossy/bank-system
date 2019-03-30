package com.odinrossy.banksystem.exceptions.user

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String message) {
        super(message)
    }
}
