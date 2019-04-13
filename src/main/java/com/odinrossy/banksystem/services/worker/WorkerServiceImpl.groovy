package com.odinrossy.banksystem.services.worker

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.exceptions.worker.WrongPasswordException
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.repositories.worker.WorkerRepository
import com.odinrossy.banksystem.services.security.AuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = 'WorkerService')
class WorkerServiceImpl implements WorkerService {


    @Autowired
    WorkerRepository workerRepository

    @Autowired
    AuthorizationService authorizationService

    @Override
    List<Worker> findAll() {
        return (List<Worker>) workerRepository.findAll()
    }

    @Override
    Worker findById(long id) throws RuntimeException {
        log.info('FindById: ' + id)

        Worker worker = workerRepository.findById(id)

        if (!worker)
            throw new ResourceNotFoundException('Worker not found. Worker id: ' + id)

        return worker
    }

    @Override
    Worker findByUsername(String username) throws RuntimeException {
        log.info('FindByUsername: ' + username)

        Worker worker = workerRepository.findByUsername(username)

        if (!worker)
            throw new ResourceNotFoundException('Worker not found. Worker username: ' + username)
        else
            log.info('Worker found. Worker information: ' + worker)

        return worker
    }

    @Override
    Worker findByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundException, WrongPasswordException {
        log.info('FindByUsernameAndPassword: ' + username + ' ' + password)

        findByUsername(username)

        Worker worker = workerRepository.findByUsernameAndPassword(username, password)

        if (worker) {
            authorizationService.putWorkerInSession(worker)
            log.info('Worker authorized in session. Worker info: '+ worker)
            return worker
        } else
            throw new WrongPasswordException("Invalid worker passport. ")
    }

    @Override
    Worker save(Worker worker) throws RuntimeException {
        log.info('save: ' + worker)
        if (worker) {
            try {
                findById(worker.id)
                throw new ResourceAlreadyExistsException('Worker already exists. Worker details: ' + worker)

            } catch (ResourceNotFoundException e) {
                e.printStackTrace()
//                todo java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: Not-null property references a transient value - transient instance must be saved before current operation : com.odinrossy.banksystem.models.worker.Worker.passport -> com.odinrossy.banksystem.models.passport.Passport
                worker = workerRepository.save(worker)

                log.info('Worker saved. Worker information: ' + worker)
                authorizationService.putWorkerInSession(worker)

                return worker
            }

        } else
            throw new ResourceNotValidException('Worker is not valid')
    }

    @Override
    Worker update(long id, Worker worker) throws RuntimeException {
        log.debug('update: ' +  id + ' ' + worker)
        if (worker) {

            findById(id)

            worker.id = id
            worker = workerRepository.save(worker)

            log.info('Worker updated. Worker information: ' + worker)
            authorizationService.putWorkerInSession(worker)

            return worker

        } else
            throw new ResourceNotValidException('Worker is not valid')
    }

    @Override
    void delete(long id) {
        log.debug('delete: ' +  id)

        Worker worker = findById(id)

        workerRepository.deleteById(id)
        log.info('Worker deleted. Worker information: ' + worker)

        if (worker == authorizationService.getWorkerFromSession())
            authorizationService.removeWorkerFromSession()
    }

    @Override
    void checkAuthorization() throws RuntimeException {
        log.debug('Checking authorization..')

        if (!authorizationService.getWorkerFromSession())
            throw new WorkerNotAuthorizedException("Worker is not authorized.")
    }
}

