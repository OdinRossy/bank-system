package com.odinrossy.banksystem.exceptions

class UserNotAuthorizedException extends RuntimeException {
    UserNotAuthorizedException(String message) {
        super(message)
    }
}
