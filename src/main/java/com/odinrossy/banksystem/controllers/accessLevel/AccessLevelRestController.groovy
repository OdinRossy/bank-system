package com.odinrossy.banksystem.controllers.accessLevel

import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import com.odinrossy.banksystem.services.accessLevel.AccessLevelService
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
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/accessLevel')
class AccessLevelRestController {

    @Autowired
    AccessLevelService accessLevelService

    @GetMapping
    List<AccessLevel> findAll() {
        try {
            return accessLevelService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    AccessLevel findById(@PathVariable short id) {
        try {
            return accessLevelService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    AccessLevel create(@RequestBody AccessLevel accessLevel) {
        try {
            return accessLevelService.save(accessLevel)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    AccessLevel update(@PathVariable short id, @RequestBody AccessLevel accessLevel) {
        try {
            return accessLevelService.update(id, accessLevel)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    ResponseEntity delete(@PathVariable short id) {
        try {
            accessLevelService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}