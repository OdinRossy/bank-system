package com.odinrossy.banksystem.exceptions.user

class UserNullException extends RuntimeException {
    UserNullException() {
        super("User is null.")
    }
}
