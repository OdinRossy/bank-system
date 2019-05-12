package com.odinrossy.banksystem.services.account.abstraction

import com.odinrossy.banksystem.models.account.AccountType
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccountTypeService extends LoggerService {

    List<AccountType> findAll()

    AccountType findById(long id)

    AccountType findByName(String name)

    AccountType save(AccountType accountType)

    AccountType update(long id, AccountType accountType)

    def delete(long id)

}