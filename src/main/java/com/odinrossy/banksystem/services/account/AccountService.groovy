package com.odinrossy.banksystem.services.account

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.account.Currency
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccountService extends LoggerService {

    List<Account> findAll()

    List<Account> findAllByClient(Client client)

    List<Account> findAllByWorker(Worker worker)

    List<Account> findAllByCurrency(Currency currency)

    Account findById(long id) throws ResourceNotFoundException

    Account save(Account account) throws ResourceNotValidException

    Account update(long id, Account account) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

    def setDateOfIssue(Account account)

    def setDateOfExpire(Account account, int years)
}
