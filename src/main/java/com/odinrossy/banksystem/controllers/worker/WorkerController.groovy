package com.odinrossy.banksystem.controllers.worker

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping(value = '/worker')
class WorkerController {

    @Autowired
    WorkerService workerService

    @Autowired
    AuthorizationService authorizationService

    @RequestMapping(value = '/{id}')
    def show(@PathVariable long id, Model model) {
        try {
            workerService.checkAuthorization()
            Worker authorizedWorker = authorizationService.getWorkerFromSession()
            Worker requestedWorker = workerService.findById(id)

            if (authorizedWorker.id == requestedWorker.id || authorizedWorker.accessLevel.id == 1 as long) {
                model.addAttribute('worker', authorizedWorker)
                return 'worker/show'
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN)
            }
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @RequestMapping(value = '/authenticate')
    def authenticate(@RequestParam String username, @RequestParam String password) {
        try {
            Worker worker = workerService.findByUsernameAndPassword(username, password)
            return 'redirect:/worker/' + worker.id
        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.localizedMessage)
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @RequestMapping(value = '/logIn')
    def logIn() {
        return 'worker/logIn'
    }

    @RequestMapping(value = '/logUp')
    def logUp() {
        return 'worker/logUp'
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
