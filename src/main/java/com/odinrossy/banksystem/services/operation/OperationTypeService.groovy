package com.odinrossy.banksystem.services.operation

import com.odinrossy.banksystem.models.operation.OperationType
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface OperationTypeService extends LoggerService{

    OperationType findById(long id)

    OperationType findByName(String name)

    List<OperationType> findAll()

    OperationType save(OperationType operationType)

    OperationType update(long id, OperationType operationType)

    def delete(long id)

}