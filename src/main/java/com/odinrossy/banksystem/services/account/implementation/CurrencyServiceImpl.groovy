package com.odinrossy.banksystem.services.account.implementation

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.repositories.account.CurrencyRepository
import com.odinrossy.banksystem.services.account.abstraction.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository

    @Override
    List<Currency> findAll() {
        return currencyRepository.findAll() as List<Currency>
    }

    @Override
    Currency findById(long id) throws ResourceNotFoundException {
        Currency currency = currencyRepository.findById(id)
        if (!currency)
            throw new ResourceNotFoundException('Currency with id: ' + id + ' not found.')

        return currency
    }

    @Override
    Currency findByName(String name) throws ResourceNotFoundException {
        Currency currency = currencyRepository.findByName(name)
        if (!currency)
            throw new ResourceNotFoundException('Currency with name: ' + name + ' not found.')

        return currency
    }

    @Override
    Currency save(Currency currency) throws ResourceNotValidException {
        try {
            findById(currency.id)
            throw new ResourceAlreadyExistsException('Currency already exists. Details: ' + currency)

        } catch (ResourceNotFoundException ignored) {
            if (!currency)
                throw new ResourceNotValidException('Currency is not valid')

            currency = currencyRepository.save(currency)
            return findById(currency.id)
        }
    }

    @Override
    Currency update(long id, Currency currency) throws ResourceNotFoundException, ResourceNotValidException {
        currency.id = id
        findById(currency.id)

        if (!currency)
            throw new ResourceNotValidException('Currency is not valid')

        currency = currencyRepository.save(currency)
        return findById(currency.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        try {
            def currencyFromDB = findById(id)
            currencyRepository.delete(currencyFromDB)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }
}
