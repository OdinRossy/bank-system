package com.odinrossy.banksystem.services.operation

import com.odinrossy.banksystem.config.BankConfig
import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.operation.Operation
import com.odinrossy.banksystem.models.operation.OperationType
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.repositories.operation.OperationRepository
import com.odinrossy.banksystem.services.security.AuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository

    @Autowired
    AuthorizationService authorizationService

    @Override
    Operation findById(long id) {
        return null
    }

    @Override
    List<Operation> findAllByAccount(Account account) {
        return operationRepository.findAllByAccount(account)
    }

    @Override
    List<Operation> findAllByWorker(Worker worker) {
        return operationRepository.findAllByWorker(worker)
    }

    @Override
    List<Operation> findAllByOperationType(OperationType operationType) {
        return operationRepository.findAllByOperationType(operationType)
    }

    @Override
    List<Operation> findAll() {
        return operationRepository.findAll() as List<Operation>
    }

    @Override
    Operation save(Operation operation) {
        try {
            findById(operation.id)
            throw new ResourceAlreadyExistsException('Operation already exists. Details: ' + operation)

        } catch (ResourceNotFoundException ignored) {
            if (!operation)
                throw new ResourceNotValidException('Operation is not valid')

            setDateOfIssue(operation)
            setDateOfExpire(operation, BankConfig.OPERATION_DURANCE)
            operation.worker = authorizationService.getWorkerFromSession()

            operation = operationRepository.save(operation)
            return findById(operation.id)
        }
    }

    @Override
    Operation update(long id, Operation operation) {
        operation.id = id
        findById(operation.id)

        if (!operation)
            throw new ResourceNotValidException('Operation is not valid')

        operation = operationRepository.save(operation)
        return findById(operation.id)
    }

    @Override
    def delete(long id) {
        try {
            def operationType = findById(id)
            operationRepository.delete(operationType)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }

    def setDateOfIssue(Operation operation) {
        operation.dateOfIssue = new Date()
    }

    def setDateOfExpire(Operation operation, int years) {
        if (!operation.dateOfIssue) {
            operation.setDateOfIssue()
        }
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, years)
        operation.dateOfExpire = calendar.getTime()
    }
}
