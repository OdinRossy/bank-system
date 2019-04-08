package com.odinrossy.banksystem.controllers.worker

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/worker')
class WorkerRestController {

    @Autowired
    WorkerService workerService

    @GetMapping
    List<Worker> getWorkersAsList() {
        try {
            return workerService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    Worker getWorker(@PathVariable long id) {
        try {
            return workerService.findById(id)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    Worker createWorker(@RequestBody Worker worker) {
        try {
            return workerService.save(worker)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    Worker updateWorker(@PathVariable long id, @RequestBody Worker worker) {
        try {
            return workerService.update(id, worker)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    ResponseEntity deleteWorker(@PathVariable long id) {
        try {
            workerService.delete(id)
            return ResponseEntity.ok().build()

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}
