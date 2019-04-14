package com.odinrossy.banksystem.services.worker

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.worker.WorkerNotAuthorizedException
import com.odinrossy.banksystem.exceptions.worker.WrongPasswordException
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface WorkerService extends LoggerService{

    List<Worker> findAll()

    Worker findById(long id)

    Worker findByUsername(String username) throws ResourceNotFoundException

    Worker findByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundException, WrongPasswordException

    Worker save(Worker user)

    Worker update(long id, Worker user)

    def delete(long id)

    void checkAuthorization() throws WorkerNotAuthorizedException

}
