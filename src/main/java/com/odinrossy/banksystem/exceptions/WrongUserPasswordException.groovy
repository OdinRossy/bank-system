package com.odinrossy.banksystem.exceptions

class WrongUserPasswordException extends RuntimeException {
    WrongUserPasswordException(String message) {
        super(message)
    }
}
