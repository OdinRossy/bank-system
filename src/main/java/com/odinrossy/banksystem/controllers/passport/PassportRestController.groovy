package com.odinrossy.banksystem.controllers.passport

import com.odinrossy.banksystem.models.passport.Passport
import com.odinrossy.banksystem.services.passport.PassportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = '/api/passport', produces = 'application/json')
class PassportRestController {

    @Autowired
    PassportService passportService

    @GetMapping
    def findAll() {
        try {
            return passportService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    def findById(@PathVariable String id) {
        try {
            return passportService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Passport passport) {
        try {
            return passportService.save(passport)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable String id, @RequestBody Passport passport) {
        try {
            return passportService.update(id, passport)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable String id) {
        try {
            passportService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping('/checkIsPassportIdValid')
    def checkIsPassportIdValid(@RequestParam String passportId, HttpServletResponse httpServletResponse) {
        try {
            def responseMap = [:]

            if (passportService.isPassportIdInUse(passportId)) {
                httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
                responseMap['message'] = 'Passport id ' + passportId + ' already in use.'
            } else {
                httpServletResponse.status = HttpServletResponse.SC_OK
                responseMap['message'] = 'Passport id ' + passportId + ' is valid.'
            }

            return responseMap

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping('/checkIsPassportNumberValid')
    def checkIsPassportNumberValid(@RequestParam String passportNumber, HttpServletResponse httpServletResponse) {
        try {
            int number = passportNumber as int
            def responseMap = [:]

            if (passportService.isPassportNumberInUse(number)) {
                httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
                responseMap['message'] = 'Passport number ' + passportNumber + ' already in use.'
            } else {
                httpServletResponse.status = HttpServletResponse.SC_OK
                responseMap['message'] = 'Passport number ' + passportNumber + ' is valid.'
            }

            return responseMap

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

}
