package com.odinrossy.banksystem.repositories.account

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.account.AccountType
import com.odinrossy.banksystem.models.account.Currency
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByCurrency(Currency currency)

    List<Account> findAllByAccountType(AccountType accountType)

}