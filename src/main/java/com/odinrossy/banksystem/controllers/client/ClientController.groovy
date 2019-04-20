package com.odinrossy.banksystem.controllers.client

import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.services.client.ClientService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping(value = '/client')
class ClientController {

    @Autowired
    WorkerService workerService

    @Autowired
    ClientService clientService

    @GetMapping
    def index(Model model) {
        try {
            workerService.checkAuthorization()

            def clients = clientService.findAll() as Set

            model.addAttribute("clients", clients)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.localizedMessage)
        }
        return 'client/index'
    }

    @GetMapping(value = '/{id}')
    def show(@PathVariable long id, Model model) {
        try {
            workerService.checkAuthorization()

            def client = clientService.findById(id)

            model.addAttribute("client", client)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.localizedMessage)
        }
        return 'client/show'
    }

    @GetMapping(value = '/create')
    def create() {
        try {
            workerService.checkAuthorization()

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.localizedMessage)
        }
        return 'client/create'
    }

}
