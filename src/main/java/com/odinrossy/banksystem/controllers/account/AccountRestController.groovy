package com.odinrossy.banksystem.controllers.account

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.account.AccountType
import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.services.account.abstraction.AccountService
import com.odinrossy.banksystem.services.account.abstraction.AccountTypeService
import com.odinrossy.banksystem.services.account.abstraction.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController(value = 'AccountRestController')
@RequestMapping(value = '/api/account')
class AccountRestController {

    @Autowired
    AccountService accountService

    @Autowired
    AccountTypeService accountTypeService

    @Autowired
    CurrencyService currencyService

    @GetMapping(value = '/{id}')
    def findById(@PathVariable long id) {
        try {
            return accountService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping
    def findAll() {
        try {
            return accountService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byAccountType/{id}')
    def findAllByClient(@PathVariable long id) {
        try {
            return accountService.findAllByAccountType(accountTypeService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byCurrency/{id}')
    def findAllByCurrency(@PathVariable long id) {
        try {
            return accountService.findAllByCurrency(currencyService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Account account) {
        try {
            return accountService.save(account)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable long id, @RequestBody Account account) {
        try {
            return accountService.update(id, account)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable long id) {
        try {
            accountService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}
