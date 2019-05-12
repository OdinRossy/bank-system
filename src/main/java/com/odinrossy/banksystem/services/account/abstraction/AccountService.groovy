package com.odinrossy.banksystem.services.account.abstraction

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.account.AccountType
import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccountService extends LoggerService {

    Account findById(long id)

    List<Account> findAll()

    List<Account> findAllByCurrency(Currency currency)

    List<Account> findAllByAccountType(AccountType currency)

    Account save(Account account)

    Account update(long id, Account account)

    def delete(long id)

    def changeValue(Account account, BigDecimal value)

}
