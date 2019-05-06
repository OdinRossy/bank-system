package com.odinrossy.banksystem.repositories.account

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository extends CrudRepository<Account, Long> {

    Account findById(long id)

    List<Account> findAllByClient(Client client)

    List<Account> findAllByWorker(Worker worker)

    List<Account> findAllByCurrency(Currency currency)

}