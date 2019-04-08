package com.odinrossy.banksystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException() {
        super()
    }

    ResourceNotFoundException(String message) {
        super(message)
    }

    ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause)
    }
}
