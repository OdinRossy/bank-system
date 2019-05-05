package com.odinrossy.banksystem.controllers.client

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.services.client.ClientService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = '/api/client', produces = 'application/json')
class ClientRestController {

    final static Logger log = LoggerFactory.getLogger(this.class)

    @Autowired
    ClientService clientService

    @GetMapping
    def findAll() {
        try {
            return clientService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    def findById(@PathVariable long id) {
        try {
            return clientService.findById(id)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Client registration) {
        try {
            return clientService.save(registration)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable long id, @RequestBody Client registration) {
        try {
            return clientService.update(id, registration)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable long id) {
        try {
            clientService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping('/checkIsEmailValid')
    def checkIsEmailValid(@RequestParam String email, HttpServletResponse httpServletResponse) {
        try {
            def responseMap = [:]

            if (clientService.isEmailInUse(email)) {
                httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
                responseMap['message'] = 'E-mail ' + email + ' already in use.'
            } else {
                httpServletResponse.status = HttpServletResponse.SC_OK
                responseMap['message'] = 'E-mail ' + email + ' is valid.'
            }

            return responseMap

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping('/checkIsMobilePhoneNumberValid')
    def checkIsMobilePhoneNumber(@RequestParam String mobilePhoneNumber, HttpServletResponse httpServletResponse) {
        try {
            def responseMap = [:]

            if (clientService.isMobilePhoneNumberInUse(mobilePhoneNumber)) {
                httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
                responseMap['message'] = 'Mobile phone number ' + mobilePhoneNumber + ' already in use.'
            } else {
                httpServletResponse.status = HttpServletResponse.SC_OK
                responseMap['message'] = 'Mobile phone number ' + mobilePhoneNumber + ' is valid.'
            }

            return responseMap

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

}
