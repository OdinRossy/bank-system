package com.odinrossy.banksystem.services.accessLevel

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import com.odinrossy.banksystem.repositories.accessLevel.AccessLevelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = 'AccessLevelService')
class AccessLevelServiceImpl implements AccessLevelService {

    @Autowired
    AccessLevelRepository accessLevelRepository

    @Override
    List<AccessLevel> findAll() {
        return accessLevelRepository.findAll() as List<AccessLevel>
    }

    @Override
    AccessLevel findById(short id) throws ResourceNotFoundException {
        def accessLevel = accessLevelRepository.findById(id)

        if (!accessLevel)
            throw new ResourceNotFoundException('Access level not found. Id:' + id)

        return accessLevel
    }

    @Override
    AccessLevel findByLabel(String label) throws ResourceNotFoundException {
        AccessLevel accessLevel = accessLevelRepository.findByLabel(label)

        if (!accessLevel)
            throw new ResourceNotFoundException('Access level not found. label:' + label)

        return accessLevel
    }

    @Override
    AccessLevel save(AccessLevel accessLevel) {
            try {
                findById(accessLevel.id)
                throw new ResourceAlreadyExistsException('AccessLevel already exists. Details: ' + accessLevel)

            } catch (ResourceNotFoundException ignored) {
                if (!accessLevel)
                    throw new ResourceNotValidException('AccessLevel is not valid')

//                todo catch java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'Plain worker' for key 'UK_9lt2v43i3s8235emubg2iudef'
                accessLevel = accessLevelRepository.save(accessLevel)
                return findById(accessLevel.id)
            }

    }

    @Override
    AccessLevel update(short id, AccessLevel accessLevel) {
        accessLevel.id = id
        findById(accessLevel.id)


        if (!accessLevel)
            throw new ResourceNotValidException('AccessLevel is not valid')

        accessLevel = accessLevelRepository.save(accessLevel)
        return findById(accessLevel.id)
    }

    @Override
    def delete(short id) {
        try {
            def accessLevelFromDB = findById(id)
            accessLevelRepository.delete(accessLevelFromDB)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }
}
