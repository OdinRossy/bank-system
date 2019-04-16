package com.odinrossy.banksystem.exceptions.worker

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.FORBIDDEN)
class WorkerNotAuthorizedException extends RuntimeException {

    WorkerNotAuthorizedException() {
    }

    WorkerNotAuthorizedException(String message) {
        super(message)
    }

    WorkerNotAuthorizedException(String message, Throwable throwable) {
        super(message, throwable)
    }
}
