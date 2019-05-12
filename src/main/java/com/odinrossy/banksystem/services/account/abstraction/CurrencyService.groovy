package com.odinrossy.banksystem.services.account.abstraction

import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CurrencyService extends LoggerService {

    List<Currency> findAll()

    Currency findById(long id)

    Currency findByName(String name)

    Currency save(Currency currency)

    Currency update(long id, Currency currency)

    def delete(long id)

}
