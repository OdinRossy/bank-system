package com.odinrossy.banksystem.services.passport

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.passport.Passport
import com.odinrossy.banksystem.repositories.passport.PassportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = 'PassportService')
class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportRepository passportRepository

    @Override
    List<Passport> findAll() {
        return passportRepository.findAll() as List<Passport>
    }

    @Override
    Passport findById(String id) throws ResourceNotFoundException {
        Passport passport = passportRepository.findById(id).orElse(null)
        if (passport)
            return passport

        else throw new ResourceNotFoundException('Passport not found. Id: ' + id)
    }

    @Override
    Passport save(Passport passport) throws ResourceNotValidException {
        if (passport) {
            try {
                findById(passport.id)
                throw new ResourceAlreadyExistsException('Passport already exists. Passport: ' + passport)

            } catch (ResourceNotFoundException ignored) {
                return passportRepository.save(passport)

            }
        } else throw new ResourceNotValidException('Passport is not valid')
    }

    @Override
    Passport update(String id, Passport passport) throws ResourceNotFoundException, ResourceNotValidException {
        if (passport) {
            passport.id = id
            findById(passport.id)

            return passportRepository.save(passport)

        } else throw new ResourceNotValidException('Passport is not valid')
    }

    @Override
    void delete(String id) throws ResourceNotFoundException {
        passportRepository.delete(findById(id))
    }
}
