package com.odinrossy.banksystem.services.country

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.country.Country
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CountryService extends LoggerService {

    List<Country> findAll()

    Country findByIso3code(String iso3code) throws ResourceNotFoundException

    Country save(Country country) throws ResourceAlreadyExistsException, ResourceNotValidException

    Country update(String iso3code, Country country) throws ResourceNotFoundException, ResourceNotValidException

    def delete(String iso3code) throws ResourceNotFoundException

}