package com.odinrossy.banksystem.controllers.operation

import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = '/operation')
class OperationController {

    @Autowired
    WorkerService workerService

    @Autowired
    AuthorizationService authorizationService

    @RequestMapping
    def index(Model model) {
        try {
            workerService.checkAuthorization()
            def worker = authorizationService.workerFromSession
            model.addAttribute('worker', worker)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
        return 'operation/index'
    }


}
