package com.odinrossy.banksystem.exceptions.worker

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
