package com.odinrossy.banksystem.repositories.operation

import com.odinrossy.banksystem.models.operation.OperationType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationTypeRepository extends CrudRepository<OperationType, Long> {

    OperationType findById(long id)

    OperationType findByName(String name)

}
