package com.odinrossy.banksystem.services.operation


import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.operation.OperationType
import com.odinrossy.banksystem.repositories.operation.OperationTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OperationTypeServiceImpl implements OperationTypeService {

    @Autowired
    OperationTypeRepository operationTypeRepository

    @Override
    OperationType findById(long id) {
        OperationType operationType = operationTypeRepository.findById(id)
        if (!operationType)
            throw new ResourceNotFoundException('OperationType with id: ' + id + ' not found.')

        return operationType
    }

    @Override
    OperationType findByName(String name) {
        OperationType operationType = operationTypeRepository.findByName(name)
        if (!operationType)
            throw new ResourceNotFoundException('OperationType with name: ' + name + ' not found.')

        return operationType
    }

    @Override
    List<OperationType> findAll() {
        return operationTypeRepository.findAll() as List<OperationType>
    }

    @Override
    OperationType save(OperationType operationType) {
        try {
            findById(operationType.id)
            throw new ResourceAlreadyExistsException('OperationType already exists. Details: ' + operationType)

        } catch (ResourceNotFoundException ignored) {
            if (!operationType)
                throw new ResourceNotValidException('OperationType is not valid')

            operationType = operationTypeRepository.save(operationType)
            return findById(operationType.id)
        }
    }

    @Override
    OperationType update(long id, OperationType operationType) {
        operationType.id = id
        findById(operationType.id)

        if (!operationType)
            throw new ResourceNotValidException('OperationType is not valid')

        operationType = operationTypeRepository.save(operationType)
        return findById(operationType.id)
    }

    @Override
    def delete(long id) {
        try {
            def operationType = findById(id)
            operationTypeRepository.delete(operationType)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }
}
