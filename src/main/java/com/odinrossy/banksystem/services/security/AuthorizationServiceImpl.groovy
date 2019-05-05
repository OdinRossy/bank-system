package com.odinrossy.banksystem.services.security

import com.odinrossy.banksystem.models.worker.Worker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.servlet.http.HttpSession

@Service
class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    HttpSession session

    @Override
    Worker getWorkerFromSession() {
        return (Worker) session.getAttribute("worker")
    }

    @Override
    void putWorkerInSession(Worker user) {
        session.setAttribute("worker", user)
    }

    @Override
    void removeWorkerFromSession() {
        session.removeAttribute("worker")
    }
}
