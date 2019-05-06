package com.odinrossy.banksystem.services.account

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CurrencyService extends LoggerService {

    List<Currency> findAll()

    Currency findById(long id) throws ResourceNotFoundException

    Currency findByName(String name) throws ResourceNotFoundException

    Currency save(Currency currency) throws ResourceNotValidException

    Currency update(long id, Currency currency) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

}
