package com.odinrossy.banksystem.controllers.passport


import com.odinrossy.banksystem.models.passport.Passport
import com.odinrossy.banksystem.services.passport.PassportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/passport')
class PassportRestController {

    @Autowired
    PassportService passportService

    @GetMapping
    List<Passport> findAll() {
        try {
            return passportService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    Passport findById(@PathVariable String id) {
        try {
            return passportService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    Passport create(@RequestBody Passport passport) {
        try {
            return passportService.save(passport)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    Passport update(@PathVariable String id, @RequestBody Passport passport) {
        try {
            return passportService.update(id, passport)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    ResponseEntity delete(@PathVariable String id) {
        try {
            passportService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

}
