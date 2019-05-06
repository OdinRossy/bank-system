package com.odinrossy.banksystem.repositories.account

import com.odinrossy.banksystem.models.account.Currency
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Currency findById(long id)

    Currency findByName(String name)

}
