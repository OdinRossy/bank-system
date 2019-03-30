package com.odinrossy.banksystem.exceptions

class UserNullException extends RuntimeException {
    UserNullException() {
        super("User is null.")
    }
}
