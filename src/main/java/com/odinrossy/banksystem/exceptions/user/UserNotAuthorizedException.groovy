package com.odinrossy.banksystem.exceptions.user

class UserNotAuthorizedException extends RuntimeException {
    UserNotAuthorizedException(String message) {
        super(message)
    }
}
