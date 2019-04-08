package com.odinrossy.banksystem.services.worker

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.worker.WrongPasswordException
import com.odinrossy.banksystem.models.worker.Worker
import org.springframework.stereotype.Service

@Service
interface WorkerService {

    List<Worker> findAll()

    Worker findById(long id)

    Worker findByUsername(String username) throws ResourceNotFoundException

    Worker findByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundException, WrongPasswordException

    Worker save(Worker user)

    Worker update(long id, Worker user)

    void delete(long id)

    void checkAuthorization()

}
