package com.odinrossy.banksystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class ResourceNotValidException extends RuntimeException {

    ResourceNotValidException() {
        super()
    }

    ResourceNotValidException(String message) {
        super(message)
    }

    ResourceNotValidException(String message, Throwable cause) {
        super(message, cause)
    }
}
