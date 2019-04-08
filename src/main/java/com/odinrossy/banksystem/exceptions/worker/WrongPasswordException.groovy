package com.odinrossy.banksystem.exceptions.worker

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class WrongPasswordException extends RuntimeException {

    WrongPasswordException(String message) {
        super(message)
    }

}
