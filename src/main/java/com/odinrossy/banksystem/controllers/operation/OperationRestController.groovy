package com.odinrossy.banksystem.controllers.operation

import com.odinrossy.banksystem.models.operation.Operation
import com.odinrossy.banksystem.services.account.AccountService
import com.odinrossy.banksystem.services.client.ClientService
import com.odinrossy.banksystem.services.operation.OperationService
import com.odinrossy.banksystem.services.operation.OperationTypeService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/operation')
class OperationRestController {

    @Autowired
    OperationService operationService

    @Autowired
    AccountService accountService

    @Autowired
    ClientService clientService

    @Autowired
    WorkerService workerService

    @Autowired
    OperationTypeService operationTypeService

    @GetMapping(value = '/{id}')
    def findById(@PathVariable long id) {
        try {
            return operationService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping
    def findAll() {
        try {
            return operationService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byAccount/{id}')
    def findAllByClient(@PathVariable long id) {
        try {
            return operationService.findAllByAccount(accountService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byWorker/{id}')
    def findAllByWorker(@PathVariable long id) {
        try {
            return operationService.findAllByWorker(workerService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byOperationType/{id}')
    def findAllByCurrency(@PathVariable long id) {
        try {
            return operationService.findAllByOperationType(operationTypeService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Operation operation) {
        try {
            return operationService.save(operation)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable long id, @RequestBody Operation operation) {
        try {
            return operationService.update(id, operation)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable long id) {
        try {
            operationService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

}
