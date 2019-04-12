package com.odinrossy.banksystem.controllers.accessLevel

import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import com.odinrossy.banksystem.services.accessLevel.AccessLevelService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/accessLevel')
class AccessLevelRestController {

    private final static Logger log = LoggerFactory.getLogger(AccessLevelRestController.class)

    @Autowired
    AccessLevelService accessLevelService

    @GetMapping
    List<AccessLevel> getAsList() {
        try {
            return accessLevelService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    AccessLevel getWorker(@PathVariable short id) {
        try {
            return accessLevelService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    AccessLevel createAccessLevel(@RequestBody AccessLevel accessLevel) {
        try {
            return accessLevelService.save(accessLevel)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    AccessLevel updateAccessLevel(@PathVariable short id, @RequestBody AccessLevel accessLevel) {
        try {
            return accessLevelService.update(id, accessLevel)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    ResponseEntity deleteAccessLevel(@PathVariable short id) {
        try {
            accessLevelService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}