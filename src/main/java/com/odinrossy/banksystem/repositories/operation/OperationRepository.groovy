package com.odinrossy.banksystem.repositories.operation

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.operation.Operation
import com.odinrossy.banksystem.models.operation.OperationType
import com.odinrossy.banksystem.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationRepository extends CrudRepository<Operation, Long> {

    Operation findById(long id)

    List<Operation> findAllByAccount(Account account)

    List<Operation> findAllByWorker(Worker worker)

    List<Operation> findAllByOperationType(OperationType operationType)

}
