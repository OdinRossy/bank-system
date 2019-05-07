package com.odinrossy.banksystem.controllers.country

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.models.country.Country
import com.odinrossy.banksystem.services.country.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/country', produces = 'application/json')
class CountryRestController {

    @Autowired
    CountryService countryService

    @GetMapping
    def findAll() {
        try {
            return countryService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{iso3code}')
    def findByIso3code(@PathVariable String iso3code) {
        try {
            return countryService.findByIso3code(iso3code)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Country country) {
        try {
            return countryService.save(country)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{iso3code}')
    def update(@PathVariable String iso3code, @RequestBody Country country) {
        try {
            return countryService.update(iso3code, country)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{iso3code}')
    def delete(@PathVariable String iso3code) {
        try {
            countryService.delete(iso3code)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

}
