package com.odinrossy.banksystem.controllers.account

import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.services.account.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController(value = 'CurrencyRestController')
@RequestMapping(value = '/api/currency')
class CurrencyRestController {

    @Autowired
    CurrencyService currencyService

    @GetMapping
    def findAll() {
        try {
            return currencyService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    def findById(@PathVariable long id) {
        try {
            return currencyService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byName/{name}')
    def findByName(@PathVariable String name) {
        try {
            return currencyService.findByName(name)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Currency currency) {
        try {
            return currencyService.save(currency)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable long id, @RequestBody Currency currency) {
        try {
            return currencyService.update(id, currency)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable long id) {
        try {
            currencyService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}
