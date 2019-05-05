package com.odinrossy.banksystem.services.passport

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.passport.Passport
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface PassportService extends LoggerService {

    List<Passport> findAll()

    Passport findById(String id) throws ResourceNotFoundException

    Passport save(Passport passport) throws ResourceNotValidException

    Passport update(String id, Passport passport) throws ResourceNotFoundException, ResourceNotValidException

    def delete(String id) throws ResourceNotFoundException

    def isPassportIdInUse(String id)

    def isPassportNumberInUse(int number)

}