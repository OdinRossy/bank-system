package com.odinrossy.banksystem.controllers.worker

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

import java.text.SimpleDateFormat

@Controller
@RequestMapping(value = "/worker")
class WorkerController {

    private final static Logger log = LoggerFactory.getLogger(WorkerController.class)

    @Autowired
    WorkerService workerService

    @Autowired
    AuthorizationService authorizationService


    @RequestMapping(value = '/authenticate')
    def authenticate(@RequestParam String username, @RequestParam String password) {
        try {
            workerService.findByUsernameAndPassword(username, password)
            return "redirect:/worker/profile"
        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.localizedMessage)
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @GetMapping(value = '/profile')
    def index(Model model) {
        try {
            workerService.checkAuthorization()
            Worker worker = authorizationService.getWorkerFromSession()
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat('dd.MM.yyyy')

            def dates = [:]
            dates.put('dateOfIssue', simpleDateFormat.format(worker.passport.dateOfIssue))
            dates.put('dateOfExpire', simpleDateFormat.format(worker.passport.dateOfExpire))
            dates.put('birthDate', simpleDateFormat.format(worker.passport.birthDate))

            model.addAllAttributes(dates)
            model.addAttribute("worker", worker)
            return "worker/profile"
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return "redirect:/worker/logIn"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @RequestMapping(value = '/logIn')
    def logIn() {
        return "worker/logIn"
    }

    @RequestMapping(value = '/logUp')
    def logUp() {
        return "worker/logUp"
    }

    @RequestMapping(value = '/logOut')
    def logOut() {
        try {
            authorizationService.removeWorkerFromSession()
            workerService.checkAuthorization()
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/'
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }
}
