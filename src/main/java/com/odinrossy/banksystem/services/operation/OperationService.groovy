package com.odinrossy.banksystem.services.operation

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.operation.Operation
import com.odinrossy.banksystem.models.operation.OperationType
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface OperationService extends LoggerService {

    Operation findById(long id)

    List<Operation> findAllByAccount(Account account)

    List<Operation> findAllByWorker(Worker worker)

    List<Operation> findAllByOperationType(OperationType operationType)

    List<Operation> findAll()

    Operation save(Operation operation)

    Operation update(long id, Operation operation)

    def delete(long id)

}