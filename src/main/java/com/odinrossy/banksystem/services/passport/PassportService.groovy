package com.odinrossy.banksystem.services.passport

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.passport.Passport
import com.odinrossy.banksystem.services.ApplicationService
import org.springframework.stereotype.Service

@Service
interface PassportService extends ApplicationService {

    List<Passport> findAll()

    Passport findById(String id) throws ResourceNotFoundException

    Passport save(Passport passport) throws ResourceNotValidException

    Passport update(String id, Passport passport) throws ResourceNotFoundException, ResourceNotValidException

    void delete(String id) throws ResourceNotFoundException
}