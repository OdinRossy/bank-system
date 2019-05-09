package com.odinrossy.banksystem.controllers.client

import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.services.account.AccountService
import com.odinrossy.banksystem.services.account.CurrencyService
import com.odinrossy.banksystem.services.client.ClientService
import com.odinrossy.banksystem.services.country.CountryService
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
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

    @Autowired
    CountryService countryService

    @Autowired
    AccountService accountService

    @Autowired
    CurrencyService currencyService

    @Autowired
    AuthorizationService authorizationService

    @RequestMapping
    def index(Model model) {
        try {
            workerService.checkAuthorization()
            def clients = clientService.findAll()
            def worker = authorizationService.workerFromSession
            model.addAttribute('worker', worker)
            model.addAttribute('clients', clients)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
        return 'client/index'
    }

    @RequestMapping(value = '/{id}')
    def show(@PathVariable long id, Model model) {
        try {
            workerService.checkAuthorization()
            def client = clientService.findById(id)
            def accounts = accountService.findAllByClient(client)
            def currencies = currencyService.findAll()
            def worker = authorizationService.workerFromSession
            def initials = clientService.getInitials(client)
            model.addAttribute('worker', worker)
            model.addAttribute('client', client)
            model.addAttribute('accounts', accounts)
            model.addAttribute('currencies', currencies)
            model.addAttribute('initials', initials)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.message, e)
        }
        return 'client/show'
    }

    @RequestMapping(value = '/create')
    def create(Model model) {
        try {
            workerService.checkAuthorization()
            def countries = countryService.findAll()
            def worker = authorizationService.workerFromSession
            model.addAttribute('worker', worker)
            model.addAttribute('countries', countries)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.message, e)
        }
        return 'client/create'
    }

    @RequestMapping(value = '/edit/{id}')
    def edit(@PathVariable long id, Model model) {
        try {
            workerService.checkAuthorization()
            def client = clientService.findById(id)
            def worker = authorizationService.workerFromSession
            model.addAttribute('worker', worker)
            model.addAttribute('client', client)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.message, e)
        }
        return 'client/edit'
    }

}
