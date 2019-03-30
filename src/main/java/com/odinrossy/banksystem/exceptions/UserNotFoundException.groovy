package com.odinrossy.banksystem.exceptions

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String message) {
        super(message)
    }
}
