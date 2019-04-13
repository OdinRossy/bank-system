package com.odinrossy.banksystem.services.registration

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.models.registration.Registration
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface RegistrationService extends LoggerService {

    List<Registration> findAll()

    List<Registration> findAllByAddress(Address address) throws ResourceNotFoundException

    Registration findById(long id) throws ResourceNotFoundException

    Registration save(Registration registration) throws ResourceNotValidException

    Registration update(long id, Registration registration) throws ResourceNotFoundException, ResourceNotValidException

    void delete(long id) throws ResourceNotFoundException

}
