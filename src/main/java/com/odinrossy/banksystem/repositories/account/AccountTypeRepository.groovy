package com.odinrossy.banksystem.repositories.account

import com.odinrossy.banksystem.models.account.AccountType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountTypeRepository extends CrudRepository<AccountType, Long> {

    AccountType findById(long id)

    AccountType findByName(String name)

}
