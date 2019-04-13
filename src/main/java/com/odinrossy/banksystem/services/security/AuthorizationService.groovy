package com.odinrossy.banksystem.services.security

import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.logger.LoggerService

interface AuthorizationService extends LoggerService {

    Worker getWorkerFromSession()

    void putWorkerInSession(Worker worker)

    void removeWorkerFromSession()

}