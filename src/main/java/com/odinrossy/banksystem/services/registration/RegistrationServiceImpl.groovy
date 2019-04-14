package com.odinrossy.banksystem.services.registration

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.models.registration.Registration
import com.odinrossy.banksystem.repositories.registration.RegistrationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository

    @Override
    List<Registration> findAll() {
        log.debug('findAll')
        return registrationRepository.findAll() as List<Registration>
    }

    @Override
    List<Registration> findAllByAddress(Address address) throws ResourceNotFoundException {
        log.debug("findAllByAddress, ${address}.")

        List<Registration> registrationList = registrationRepository.findAllByAddress(address)

        if (registrationList.size() == 0)
            throw new ResourceNotFoundException("No Registrations found for Address: ${address}")

        return registrationList
    }

    @Override
    Registration findById(long id) throws ResourceNotFoundException {
        log.debug("findById, id: ${id}.")

        Registration registrationFromDB = registrationRepository.findById(id)

        if (!registrationFromDB)
            throw new ResourceNotFoundException("Registration not found. Id: ${id}")

        return registrationFromDB
    }

    @Override
    Registration save(Registration registration) throws ResourceNotValidException {
        log.debug("save, ${registration}.")

        try {
            def addressFromDB = findById(registration.id)
            throw new ResourceAlreadyExistsException("Registration already exists. ${addressFromDB}")

        } catch (ResourceNotFoundException ignored) {
            if (!registration) {
                throw new ResourceNotValidException("Registration not valid. ${registration}")
            }
            registration = registrationRepository.save(registration)

            return findById(registration.id)
        }
    }

    @Override
    Registration update(long id, Registration registration) throws ResourceNotFoundException, ResourceNotValidException {
        registration.id = id
        log.debug("update, ${registration}.")

        findById(registration.id)

        if (!registration) {
            throw new ResourceNotValidException("Registration not valid. ${registration}")
        }

        registration = registrationRepository.save(registration)

        return findById(registration.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        log.debug("delete, id: ${id}.")

        registrationRepository.delete(findById(id))
    }

}
