package com.odinrossy.banksystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)

class ResourceAlreadyExistsException extends RuntimeException {

    ResourceAlreadyExistsException() {
    }

    ResourceAlreadyExistsException(String message) {
        super(message)
    }

    ResourceAlreadyExistsException(String message, Throwable var2) {
        super(message, var2)
    }
}
