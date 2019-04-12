package com.odinrossy.banksystem.services.security

import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.ApplicationService

interface AuthorizationService extends ApplicationService {

    Worker getWorkerFromSession()

    void putWorkerInSession(Worker worker)

    void removeWorkerFromSession()

}