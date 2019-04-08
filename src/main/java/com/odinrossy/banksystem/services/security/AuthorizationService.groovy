package com.odinrossy.banksystem.services.security

import com.odinrossy.banksystem.models.worker.Worker

interface AuthorizationService {

    Worker getWorkerFromSession()

    void putWorkerInSession(Worker worker)

    void removeWorkerFromSession()

}